package hust.soict.hedspi.aims.media;

public class Track implements Playable {

    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    @Override
    public void play() {
        System.out.println("Playing track: " + title);
        System.out.println("Length: " + length);
    }
}