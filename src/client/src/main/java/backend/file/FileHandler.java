package backend.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.file.BlockBuffer.Mode;

/**
 * This class keeps track of open files.
 * 
 * @author iiMaXii
 *
 */
public final class FileHandler {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static HashMap<String, BlockBuffer> files = new HashMap<String, BlockBuffer>();

	private FileHandler() {}

	/**
	 * Get a file handle for reading. If the file is not open an attempt will be
	 * made to open it for reading.
	 * 
	 * @param filename
	 * @return The requested file, may be null if the file does not exist or if
	 *         an IOException occurred
	 */
	public static synchronized BlockBuffer read(String filename) {
		BlockBuffer file = files.get(filename);

		if (file == null) {
			try {
				file = new BlockBuffer(filename, BlockBuffer.Mode.READ);
				files.put(filename, file);
			} catch (NoSuchFileException e) {
				LOG.log(Level.WARNING, "Unable to open file {0} for reading (no such file)", filename);
			} catch (IOException e) {
				LOG.log(Level.WARNING, "Unable to open file {0} for reading", filename);
			}
		}

		return file;
	}

	/**
	 * Get the file handle for reading and writing. If the file is not open an
	 * attempt will be made to open it for reading and writing.
	 * 
	 * @param filename
	 * @return The requested file, may be null if the file does not exist or if
	 *         an IOException occurred
	 */
	public static synchronized BlockBuffer write(String filename) {
		BlockBuffer file = files.get(filename);

		if (file != null && file.getMode() == Mode.READ) {
			LOG.log(Level.INFO, "Reopening {0} for reading and writing", filename);

			files.remove(filename);
			try {
				file.close();
			} catch (IOException e) {
				LOG.log(Level.WARNING, e.toString(), e);
			}

			file = null;
		}

		if (file == null) {
			try {
				file = new BlockBuffer(filename, BlockBuffer.Mode.READ_WRITE);
				files.put(filename, file);
			} catch (NoSuchFileException e) {
				LOG.log(Level.WARNING, "Unable to open file {0} for reading and writing (no such file)", filename);
			} catch (IOException e) {
				LOG.log(Level.WARNING, "Unable to open file {0} for reading and writing", filename);
			}
		}

		return file;
	}

	/**
	 * Attempt to create a new file for reading and writing. Will fail if the
	 * file already exists.
	 * 
	 * @param filename
	 * @return The requested file, may be null if the file already exist or if
	 *         an IOException occurred
	 */
	public static synchronized BlockBuffer create(String filename) {
		BlockBuffer file = files.get(filename);

		if (file != null) {
			// The file already exists, cannot create another one
			return null;
		}

		if (file == null) {
			try {
				file = new BlockBuffer(filename, BlockBuffer.Mode.CREATE_READ_WRITE);
				files.put(filename, file);
			} catch (FileAlreadyExistsException e) {
				LOG.log(Level.WARNING, "Unable to create file {0} (file already exists)", filename);
			} catch (IOException e) {
				LOG.log(Level.WARNING, e.toString(), e);
			}
		}

		return file;
	}

	public static synchronized void destroy() {
		for (BlockBuffer blockBuffer : files.values()) {
			try {
				blockBuffer.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.toString(), e);
			}
		}

		files.clear();
	}

	public static synchronized void setReadOnly(String filename) {
		BlockBuffer file = files.get(filename);

		if (file == null) {
			throw new RuntimeException("Unable to set file " + filename + " as read only, file is not open");
		}

		try {
			file.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.toString(), e);
		}

		try {
			files.put(filename, new BlockBuffer(filename, Mode.READ));
		} catch (NoSuchFileException e) {
			LOG.log(Level.SEVERE, e.toString(), e);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.toString(), e);
		}
	}
}
