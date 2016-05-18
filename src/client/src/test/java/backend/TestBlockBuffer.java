package backend;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backend.file.BlockBuffer;

public class TestBlockBuffer {

	@Before
	public void setUp() throws Exception {
		File file = new File("tmp/");
		if (!file.exists()) {
			if (!file.mkdir()) {
				throw new Exception("Unable to create tmp/ folder");
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		(new File("tmp/TestReadWrite.bin")).delete();
		(new File("tmp/")).delete();
	}

	@Test(expected = NoSuchFileException.class)
	public void testOpenReadNonExistent() throws NoSuchFileException, IOException {
		new BlockBuffer("tmp/file_that_does_not_exist", BlockBuffer.Mode.READ);
	}

	@Test(expected = NoSuchFileException.class)
	public void testOpenReadWriteNonExistent() throws NoSuchFileException, IOException {
		new BlockBuffer("tmp/file_that_does_not_exist", BlockBuffer.Mode.READ_WRITE);
	}

	@Test
	public void createReadWrite() throws IOException {
		BlockBuffer b = new BlockBuffer("tmp/TestReadWrite.bin", BlockBuffer.Mode.CREATE_READ_WRITE);

		// Write a block to file
		byte[] buffer = new byte[BlockBuffer.BLOCK_SIZE];
		Arrays.fill(buffer, (byte) 'A');
		assertTrue("Unable to set block", b.setBlock(buffer, buffer.length, 0));

		// Read the same block from file
		byte[] readBytes = new byte[BlockBuffer.BLOCK_SIZE];
		assertEquals("Unable to get block", b.getBlock(readBytes, 0), BlockBuffer.BLOCK_SIZE);

		assertTrue(Arrays.equals(buffer, readBytes));

		b.close();
	}
}
