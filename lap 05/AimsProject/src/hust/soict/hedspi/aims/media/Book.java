package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

// Từ khóa "extends Media" giúp Book thừa kế toàn bộ thuộc tính của Media
public class Book extends Media {
    
    private List<String> authors = new ArrayList<String>();

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    // Hàm thêm tác giả (kiểm tra không cho phép trùng tên)
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Tác giả '" + authorName + "' đã được thêm.");
        } else {
            System.out.println("Tác giả '" + authorName + "' đã tồn tại!");
        }
    }

    // Hàm xóa tác giả
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Tác giả '" + authorName + "' đã được xóa.");
        } else {
            System.out.println("Không tìm thấy tác giả '" + authorName + "'.");
        }
    }
    @Override
    public String toString() {
        return "Book - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " $";
    }
}