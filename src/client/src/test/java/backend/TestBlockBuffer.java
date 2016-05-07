package backend;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		(new File("tmp/writeread.bin")).delete();
		(new File("tmp/")).delete();
	}

	@Test(expected = NoSuchFileException.class)
	public void testOpenReadNonExistent() throws NoSuchFileException, IOException {
		BlockBuffer.openRead("tmp/file_that_does_not_exist", 10);
	}

	@Test(expected = NoSuchFileException.class)
	public void testOpenReadWriteNonExistent() throws NoSuchFileException, IOException {
		BlockBuffer.openReadWrite("tmp/file_that_does_not_exist", 10);
	}

	@Test
	public void createReadWrite() throws IOException {
		BlockBuffer b = BlockBuffer.createReadWrite("tmp/TestReadWrite.bin", 10);

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