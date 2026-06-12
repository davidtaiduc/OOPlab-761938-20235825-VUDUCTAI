package hust.soict.hedspi.aims;

import java.util.Scanner;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.Playable;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initData();
        showMenu();
    }

    public static void initData() {
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "Various", 20.00f);
        Book book = new Book("Java Programming", "Education", 25.50f);
        
        store.addMedia(dvd);
        store.addMedia(cd);
        store.addMedia(book);
    }

    public static void showMenu() {
        int choice;
        do {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    updateStoreMenu();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    public static void storeMenu() {
        int choice;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Nhap tieu de media: ");
                    String title1 = scanner.nextLine();
                    Media m1 = findMediaInStore(title1);
                    if (m1 != null) {
                        System.out.println(m1.toString());
                        mediaDetailsMenu(m1);
                    } else {
                        System.out.println("Khong tim thay media!");
                    }
                    break;
                case 2:
                    System.out.println("Nhap tieu de media: ");
                    String title2 = scanner.nextLine();
                    Media m2 = findMediaInStore(title2);
                    if (m2 != null) {
                        cart.addMedia(m2);
                    } else {
                        System.out.println("Khong tim thay media!");
                    }
                    break;
                case 3:
                    System.out.println("Nhap tieu de media: ");
                    String title3 = scanner.nextLine();
                    Media m3 = findMediaInStore(title3);
                    if (m3 != null) {
                        playMedia(m3);
                    } else {
                        System.out.println("Khong tim thay media!");
                    }
                    break;
                case 4:
                    cart.print();
                    cartMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    public static void mediaDetailsMenu(Media media) {
        int choice;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    playMedia(media);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    public static void updateStoreMenu() {
        System.out.println("1. Them media vao cua hang");
        System.out.println("2. Xoa media khoi cua hang");
        System.out.println("0. Quay lai");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            System.out.println("Nhap tieu de: ");
            String title = scanner.nextLine();
            System.out.println("Nhap danh muc: ");
            String category = scanner.nextLine();
            System.out.println("Nhap gia: ");
            float cost = scanner.nextFloat();
            scanner.nextLine();
            
            Book newBook = new Book(title, category, cost);
            store.addMedia(newBook);
        } else if (choice == 2) {
            System.out.println("Nhap tieu de can xoa: ");
            String title = scanner.nextLine();
            Media m = findMediaInStore(title);
            if (m != null) {
                store.removeMedia(m);
            } else {
                System.out.println("Khong tim thay media trong cua hang!");
            }
        }
    }

    public static void cartMenu() {
        int choice;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("1. Loc theo ID");
                    System.out.println("2. Loc theo Tieu de");
                    int filterChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (filterChoice == 1) {
                        System.out.println("Nhap ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        cart.searchById(id);
                    } else if (filterChoice == 2) {
                        System.out.println("Nhap Tieu de: ");
                        String title = scanner.nextLine();
                        cart.searchByTitle(title);
                    }
                    break;
                case 2:
                    System.out.println("1. Sap xep theo Tieu de -> Gia");
                    System.out.println("2. Sap xep theo Gia -> Tieu de");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (sortChoice == 1) {
                        cart.sortByTitleCost();
                        cart.print();
                    } else if (sortChoice == 2) {
                        cart.sortByCostTitle();
                        cart.print();
                    }
                    break;
                case 3:
                    System.out.println("Nhap tieu de media can xoa: ");
                    String titleToRemove = scanner.nextLine();
                    Media mToRemove = findMediaInCart(titleToRemove);
                    if (mToRemove != null) {
                        cart.removeMedia(mToRemove);
                    } else {
                        System.out.println("Khong tim thay trong gio hang!");
                    }
                    break;
                case 4:
                    System.out.println("Nhap tieu de media can phat: ");
                    String titleToPlay = scanner.nextLine();
                    Media mToPlay = findMediaInCart(titleToPlay);
                    if (mToPlay != null) {
                        playMedia(mToPlay);
                    } else {
                        System.out.println("Khong tim thay trong gio hang!");
                    }
                    break;
                case 5:
                    System.out.println("Don hang da duoc tao thanh cong!");
                    cart.empty();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    private static Media findMediaInStore(String title) {
        for (Media m : store.getItemsInStore()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    private static Media findMediaInCart(String title) {
        for (Media m : cart.getItemsOrdered()) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    private static void playMedia(Media media) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println(e.getMessage());
                System.err.println(e.toString());
                e.printStackTrace();
            }
        } else {
            System.out.println("Media nay khong the phat!");
        }
    }
}
