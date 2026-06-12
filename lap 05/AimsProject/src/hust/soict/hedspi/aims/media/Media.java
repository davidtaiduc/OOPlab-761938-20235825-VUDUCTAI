package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparable<Media> {
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nbMedia = 0;

    public Media() {
        this.id = ++nbMedia;
    }

    public Media(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
        this.id = ++nbMedia;
    }

    public Media(String title, String category, float cost) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = ++nbMedia;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Media)) {
            return false;
        }

        Media media = (Media) obj;

        if (this.getTitle() == null || media.getTitle() == null) {
            return false;
        }

        return this.getTitle().equals(media.getTitle())
                && Float.compare(this.getCost(), media.getCost()) == 0;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare Media with null.");
        }

        String thisTitle = this.getTitle();
        String otherTitle = other.getTitle();

        if (thisTitle == null && otherTitle == null) {
            return Float.compare(other.getCost(), this.getCost());
        }
        if (thisTitle == null) {
            return -1;
        }
        if (otherTitle == null) {
            return 1;
        }

        int titleDiff = thisTitle.compareToIgnoreCase(otherTitle);
        if (titleDiff != 0) {
            return titleDiff;
        }

        return Float.compare(other.getCost(), this.getCost());
    }
}
