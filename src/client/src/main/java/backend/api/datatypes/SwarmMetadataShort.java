package backend.api.datatypes;

public class SwarmMetadataShort {
	private String id;
	private String filename;

	public SwarmMetadataShort(String id, String filename) {
		this.id = id;
		this.filename = filename;
	}

	public String getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}
}
