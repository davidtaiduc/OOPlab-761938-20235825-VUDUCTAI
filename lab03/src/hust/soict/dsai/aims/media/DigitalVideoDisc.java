package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    public DigitalVideoDisc(String title) {
        super(title, null, 0, null, 0);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost, null, 0);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, cost, director, 0);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, director, length);
    }

    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength());
        } else {
            System.out.println("DVD " + getTitle() + " cannot be played due to invalid length.");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + "$";
    }
}