package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        // Tạo một giỏ hàng mới
        Cart cart = new Cart();
        
        // Tạo các đối tượng DVD và thêm vào giỏ
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);
        
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);
        
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);
        
        // Kiểm tra phương thức in giỏ hàng
        cart.print();
        
        // Kiểm tra phương thức tìm kiếm
        System.out.println("\n--- Search by Title: Star Wars ---");
        cart.searchByTitle("Star Wars");
        
        System.out.println("\n--- Search by Title: Iron Man ---");
        cart.searchByTitle("Iron Man");
    }
}