package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;

public class PlayableTest {
    public static void main(String[] args) {
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);

        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "Various Artists", 20.00f);
        Track track1 = new Track("Song 1", 3);
        Track track2 = new Track("Song 2", 4);
        Track track3 = new Track("Song 3", 0);

        cd.addTrack(track1);
        cd.addTrack(track2);
        cd.addTrack(track3);

        System.out.println("--- Kiem tra DVD ---");
        playMedia(dvd);

        System.out.println("\n--- Kiem tra CD ---");
        playMedia(cd);
    }

    private static void playMedia(hust.soict.hedspi.aims.media.Playable media) {
        try {
            media.play();
        } catch (PlayerException e) {
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}
