package models;

import java.sql.Blob;

public class ImagemExame {

	private Blob file;
	
	public ImagemExame(Blob file) {
		this.setFile(file);
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}
	
	
}
