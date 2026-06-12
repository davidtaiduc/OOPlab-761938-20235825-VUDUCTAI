package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be empty.");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Track length cannot be negative.");
        }
        this.title = title;
        this.length = length;
    }
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return this.getTitle() != null && 
               this.getTitle().equals(track.getTitle()) && 
               this.getLength() == track.getLength();
    }
}