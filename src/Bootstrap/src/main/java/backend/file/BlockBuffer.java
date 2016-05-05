package backend.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Read and write blocks from a file.
 * 
 * This implementation limits the file size to Integer.MAX_VALUE * 1024 bytes
 * (about 2 TB, depending on the JVM)
 * 
 * @author iiMaXii
 *
 */
public class BlockBuffer {
	public static final int BLOCK_SIZE = 1024;

	private FileChannel fileChannel;
	private int blockCount;

	private BlockBuffer() {
	}

	/**
	 * Open a file for reading
	 * 
	 * @param filename
	 *            Path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @return
	 * @throws IOException
	 * @throws NoSuchFileException
	 */
	public static BlockBuffer openRead(String filename, int blockCount) throws IOException, NoSuchFileException {
		BlockBuffer fileReader = new BlockBuffer();
		fileReader.blockCount = blockCount;

		Path filePath = FileSystems.getDefault().getPath(filename);
		File file = filePath.toFile();
		if (!file.exists()) {
			throw new NoSuchFileException(filename);
		}

		fileReader.fileChannel = FileChannel.open(filePath, StandardOpenOption.READ);
		// fileReader.blockCount = (int) (1 + fileReader.fileChannel.size() / BLOCK_SIZE);

		return fileReader;
	}

	/**
	 * Open an existing file for reading and writing
	 * 
	 * @param filename
	 *            Path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @return
	 * @throws IOException
	 * @throws NoSuchFileException
	 */
	public static BlockBuffer openReadWrite(String filename, int blockCount) throws IOException, NoSuchFileException {
		BlockBuffer fileReader = new BlockBuffer();
		fileReader.blockCount = blockCount;

		Path filePath = FileSystems.getDefault().getPath(filename);
		File file = filePath.toFile();
		if (!file.exists()) {
			throw new NoSuchFileException(filename);
		}

		fileReader.fileChannel = FileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE);

		return fileReader;
	}

	/**
	 * Creates a new file for reading and writing. If the file already exists it
	 * will be truncated to zero bytes
	 * 
	 * @param filename
	 *            Path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static BlockBuffer createReadWrite(String filename, int blockCount) throws IOException {
		BlockBuffer fileReader = new BlockBuffer();
		fileReader.blockCount = blockCount;

		Path filePath = FileSystems.getDefault().getPath(filename);
		File file = filePath.toFile();
		if (!file.exists()) {
			file.createNewFile();
		}

		fileReader.fileChannel = FileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

		return fileReader;
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
	 * Get the number of blocks in the file
	 * 
	 * @return Number of blocks in the file
	 */
	public int getBlockCount() {
		return blockCount;
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
