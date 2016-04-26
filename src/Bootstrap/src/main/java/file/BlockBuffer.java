package file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * TODO:
 * - Find a way to save availableBlocks. Maybe in filename+".metadata"
 * - Unit tests
 * 
 * @author iiMaXii
 *
 */
public class BlockBuffer {
	public static final int BLOCK_SIZE = 1024;

	private FileChannel fileChannel;
	private int blockCount;
	private boolean[] availableBlocks;

	/**
	 * Open a file for reading
	 * 
	 * @param filename
	 *            Path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @return
	 * @throws IOException
	 */
	public static BlockBuffer forRead(String filename) throws IOException {
		BlockBuffer fileReader = new BlockBuffer();
		fileReader.availableBlocks = new boolean[fileReader.blockCount];

		Path filePath = FileSystems.getDefault().getPath(filename);
		fileReader.fileChannel = FileChannel.open(filePath, StandardOpenOption.READ);

		fileReader.blockCount = (int) (1 + fileReader.fileChannel.size() / BLOCK_SIZE);

		Arrays.fill(fileReader.availableBlocks, true); // TODO

		return fileReader;
	}

	/**
	 * Open a file for reading and writing
	 * 
	 * @param filename
	 *            Path to the file
	 * @param blockCount
	 *            The number of blocks in the file
	 * @return
	 * @throws IOException
	 */
	public static BlockBuffer forReadWrite(String filename, int blockCount) throws IOException {
		BlockBuffer fileReader = new BlockBuffer();
		fileReader.blockCount = blockCount;
		fileReader.availableBlocks = new boolean[fileReader.blockCount];

		Path filePath = FileSystems.getDefault().getPath(filename);
		File file = filePath.toFile();
		if (!file.exists()) {
			file.createNewFile();
		} else {
			// Resume download
			// TODO Update availableBlocks
		}

		fileReader.fileChannel = FileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE);

		return fileReader;
	}

	private BlockBuffer() {
	}

	boolean hasBlock(int blockNumber) {
		return availableBlocks[blockNumber];
	}

	/**
	 * Read a block from the file
	 * 
	 * @param buffer
	 * @param blockNumber
	 * @return Number of read bytes or -1 if and error occurred
	 * @throws IOException
	 */
	int getBlock(byte[] buffer, int blockNumber) throws IOException {
		if (buffer == null) {
			throw new NullPointerException();
		}

		if (buffer.length != BLOCK_SIZE) {
			throw new IllegalArgumentException("Buffer must be of size BLOCK_SIZE");
		}

		if (!hasBlock(blockNumber)) {
			return -1;
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
	 * @return
	 * @throws IOException
	 */
	int setBlock(byte[] buffer, int blockSize, int blockNumber) throws IOException {
		if (buffer == null) {
			throw new NullPointerException();
		}

		if (buffer.length != BLOCK_SIZE) {
			throw new IllegalArgumentException("Buffer must be of size BLOCK_SIZE");
		}

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		return fileChannel.write(byteBuffer, blockNumber * BLOCK_SIZE);
	}

	/**
	 * Get the number of blocks in the file
	 * 
	 * @return Number of blocks in the file
	 */
	int getBlockCount() {
		return blockCount;
	}

	/**
	 * Close the underlying file channel
	 * 
	 * @throws IOException
	 */
	void close() throws IOException {
		fileChannel.close();
	}

}
