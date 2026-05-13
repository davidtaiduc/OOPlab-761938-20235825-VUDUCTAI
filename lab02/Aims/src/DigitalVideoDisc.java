package bo;

public class DigitalVideoDisc {
    private static int nbDigitalVideoDiscs = 0; // Đếm số DVD đã tạo
    private final int id; // ID duy nhất
    private String title, category = "Unknown", director = "Unknown";
    private int length = 0;
    private float cost = 0.0f;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.id = ++nbDigitalVideoDiscs;
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this(title, category, director, 0, cost);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, "Unknown", 0, cost);
    }

    public DigitalVideoDisc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }

    public DigitalVideoDisc(float cost) {
        this("Unknown", "Unknown", "Unknown", 0, cost);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }

    public static int getTotalDVDs() { return nbDigitalVideoDiscs; }


    public String toString() {
        return "DVD #" + id + ": " + title + " | " + category + " | " + director +
               " | " + length + " min | $" + cost;
    }
}