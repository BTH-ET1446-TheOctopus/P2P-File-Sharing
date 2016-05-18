package backend.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * Read and write blocks from a file.
 * 
 * This implementation limits the file size to Integer.MAX_VALUE * 1024 bytes
 * (about 2 TB, depending on the JVM)
 * 
 * TODO: Make thread safe
 * 
 * @author iiMaXii
 *
 */
public class BlockBuffer {
	public static final int BLOCK_SIZE = 1024;

	private FileChannel fileChannel;
	private Mode mode;

	public enum Mode {
		READ, READ_WRITE, CREATE_READ_WRITE
	}

	/**
	 * Open a file
	 * 
	 * <table border=1 cellpadding=5 summary="">
	 * <tr> <th>Option</th> <th>Description</th> </tr>
	 * <tr>
	 *   <td>{@link Mode#READ READ}</td>
	 *   <td> Open the file for reading only. If the file does not exist NoSuchFileException will be thrown. </td>
	 * </tr>
	 * <tr>
	 *   <td>{@link Mode#READ_WRITE READ_WRITE}</td>
	 *   <td>Open the file for reading and writing. If the file does not exist NoSuchFileException will be thrown. </td>
	 * </tr>
	 * <tr>
	 *   <td>{@link Mode#CREATE_READ_WRITE CREATE_READ_WRITE}</td>
	 *   <td>Open the file for reading and writing. Will attempt to create the file, failing if the file already exists. </td>
	 * </tr>
	 * </table>
	 * 
	 * @param filename
	 *            The path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @param mode
	 * @throws IOException
	 * @throws NoSuchFileException
	 */
	public BlockBuffer(String filename, Mode mode) throws NoSuchFileException, IOException {
		this.mode = mode;

		Path filePath = FileSystems.getDefault().getPath(filename);
		File file = filePath.toFile();
		if (mode != Mode.CREATE_READ_WRITE && !file.exists()) {
			throw new NoSuchFileException(filename);
		}

		EnumSet<StandardOpenOption> openOptions = EnumSet.of(StandardOpenOption.READ);
		switch (mode) {
		case CREATE_READ_WRITE:
			openOptions.add(StandardOpenOption.CREATE_NEW);
		case READ_WRITE:
			openOptions.add(StandardOpenOption.WRITE);
		case READ:
			// To suppress warning
			break;
		}

		fileChannel = FileChannel.open(filePath, openOptions);
		// fileReader.blockCount = (int) (1 + fileReader.fileChannel.size() / BLOCK_SIZE);
	}

	/**
	 * Read a block from the file
	 * 
	 * @param buffer
	 * @param blockNumber
	 * @return The number of bytes read, possibly zero, or -1 if the given
	 *         position is greater than or equal to the file's current size
	 * @throws IOException
	 */
	public int getBlock(byte[] buffer, int blockNumber) throws IOException {
		if (buffer == null) {
			throw new NullPointerException();
		}

		if (buffer.length != BLOCK_SIZE) {
			throw new IllegalArgumentException("Buffer must be of size BLOCK_SIZE");
		}

		ByteBuffer byteBuffer = ByteBuffer.allocate(BLOCK_SIZE);
		int readBytes = fileChannel.read(byteBuffer, blockNumber * BLOCK_SIZE);

		byteBuffer.position(0);
		byteBuffer.get(buffer, 0, readBytes);
		return readBytes;
	}

	/**
	 * Write a block to the file
	 * 
	 * @param buffer
	 * @param blockSize
	 * @param blockNumber
	 * @return If the buffer was successfully written
	 * @throws IOException
	 */
	public boolean setBlock(byte[] buffer, int blockSize, int blockNumber) throws IOException {
		if (buffer == null) {
			throw new NullPointerException();
		}

		if (blockSize < 0 || blockSize > BLOCK_SIZE) {
			throw new IllegalArgumentException("Invalid blockSize");
		}

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, blockSize);
		return fileChannel.write(byteBuffer, blockNumber * BLOCK_SIZE) == blockSize;
	}

	/**
	 * Get the mode that was used to create the file
	 * 
	 * @return The file mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Close the underlying file channel
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		fileChannel.close();
	}
}
