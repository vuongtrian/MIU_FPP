package domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class CD extends Product{
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "CD{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				"artist='" + artist + '\'' +
				'}';
	}
}
