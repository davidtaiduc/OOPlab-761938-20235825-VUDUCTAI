package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    @Override
    public void play() {
        System.out.println("Playing CD: " + getTitle());
        for (Track t : tracks) {
            t.play();
        }
    }
}