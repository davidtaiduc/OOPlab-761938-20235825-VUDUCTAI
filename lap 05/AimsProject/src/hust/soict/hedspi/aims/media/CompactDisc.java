package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Media implements Playable {
    
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added.");
        } else {
            System.out.println("Track already exists.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed.");
        } else {
            System.out.println("Track not found.");
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD length: " + this.getLength());

        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                System.err.println("Error in track: " + track.getTitle());
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }
    
}