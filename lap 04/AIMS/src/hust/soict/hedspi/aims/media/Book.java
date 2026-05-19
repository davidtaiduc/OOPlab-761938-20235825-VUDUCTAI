package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    private ArrayList<String> authors = new ArrayList<>();

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public void addAuthor(String author) {
        authors.add(author);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }
}