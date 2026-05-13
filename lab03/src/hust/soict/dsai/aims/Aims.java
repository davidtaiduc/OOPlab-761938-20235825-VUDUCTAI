
package hust.soict.dsai.aims	;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Aims {
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static Store store = new Store();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        // Initialize store with sample media
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Book book1 = new Book("Java Programming", "Education", 29.99f);
        book1.addAuthor("John Doe");
        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Music", 15.99f, null, "Various Artists");
        cd1.addTrack(new Track("Song 1", 180));
        cd1.addTrack(new Track("Song 2", 200));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(cd1);

        // Start the application
        showMenu();
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    System.out.println("Exiting AIMS. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    showMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            showMenu();
        }
    }

    public static void storeMenu() {
        // Hiển thị tất cả media trong store
        store.print();

        // Hiển thị menu con
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter media title: ");
                    String title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        System.out.println(media.toString());
                        mediaDetailsMenu(media);
                    } else {
                        System.out.println("Media not found.");
                        storeMenu();
                    }
                    break;
                case 2:
                    System.out.println("Enter media title to add to cart: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media != null) {
                        cart.addMedia(media);
                        System.out.println("Number of items in cart: " + cart.getItemsOrdered().size());
                    } else {
                        System.out.println("Media not found.");
                    }
                    storeMenu();
                    break;
                case 3:
                    System.out.println("Enter media title to play: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("Media cannot be played or not found.");
                    }
                    storeMenu();
                    break;
                case 4:
                    cartMenu();
                    break;
                case 0:
                    showMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    storeMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            storeMenu();
        }
    }

    public static void mediaDetailsMenu(Media media) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (media instanceof Playable) {
            System.out.println("2. Play");
        }
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: " + (media instanceof Playable ? "0-1-2" : "0-1"));

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    System.out.println("Number of items in cart: " + cart.getItemsOrdered().size());
                    storeMenu();
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                        storeMenu();
                    } else {
                        System.out.println("Invalid choice.");
                        mediaDetailsMenu(media);
                    }
                    break;
                case 0:
                    storeMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    mediaDetailsMenu(media);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            mediaDetailsMenu(media);
        }
    }

    public static void updateStoreMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add a media");
        System.out.println("2. Remove a media");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter media type (DVD/Book/CD): ");
                    String type = scanner.nextLine();
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter category: ");
                    String category = scanner.nextLine();

                    float cost = 0;
                    boolean validCost = false;
                    while (!validCost) {
                        System.out.println("Enter cost (e.g., 19.95): ");
                        try {
                            cost = scanner.nextFloat();
                            if (cost < 0) {
                                System.out.println("Cost must be non-negative.");
                            } else {
                                validCost = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid cost format. Please enter a number (e.g., 19.95).");
                            scanner.nextLine(); // Clear invalid input
                        }
                    }
                    scanner.nextLine(); // Consume newline after nextFloat

                    Media media;
                    if (type.equalsIgnoreCase("DVD")) {
                        System.out.println("Enter director: ");
                        String director = scanner.nextLine();
                        System.out.println("Enter length: ");
                        int length = 0;
                        boolean validLength = false;
                        while (!validLength) {
                            try {
                                length = scanner.nextInt();
                                if (length < 0) {
                                    System.out.println("Length must be non-negative.");
                                } else {
                                    validLength = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid length. Please enter a number.");
                                scanner.nextLine(); // Clear invalid input
                            }
                        }
                        scanner.nextLine(); // Consume newline
                        media = new DigitalVideoDisc(title, category, director, length, cost);
                    } else if (type.equalsIgnoreCase("Book")) {
                        media = new Book(title, category, cost);
                        System.out.println("Enter author (or 'done' to finish): ");
                        String author;
                        while (!(author = scanner.nextLine()).equalsIgnoreCase("done")) {
                            ((Book) media).addAuthor(author);
                            System.out.println("Enter next author (or 'done'): ");
                        }
                    } else if (type.equalsIgnoreCase("CD")) {
                        System.out.println("Enter artist: ");
                        String artist = scanner.nextLine();
                        System.out.println("Enter director: ");
                        String director = scanner.nextLine();
                        media = new CompactDisc(title, category, cost, director, artist);
                        System.out.println("Enter track title (or 'done' to finish): ");
                        String trackTitle;
                        while (!(trackTitle = scanner.nextLine()).equalsIgnoreCase("done")) {
                            System.out.println("Enter track length: ");
                            int trackLength = 0;
                            boolean validTrackLength = false;
                            while (!validTrackLength) {
                                try {
                                    trackLength = scanner.nextInt();
                                    if (trackLength < 0) {
                                        System.out.println("Track length must be non-negative.");
                                    } else {
                                        validTrackLength = true;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid track length. Please enter a number.");
                                    scanner.nextLine(); // Clear invalid input
                                }
                            }
                            scanner.nextLine(); // Consume newline
                            ((CompactDisc) media).addTrack(new Track(trackTitle, trackLength));
                            System.out.println("Enter next track title (or 'done'): ");
                        }
                    } else {
                        System.out.println("Invalid media type.");
                        updateStoreMenu();
                        return;
                    }
                    store.addMedia(media);
                    updateStoreMenu();
                    break;
                case 2:
                    System.out.println("Enter media title to remove: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media != null) {
                        store.removeMedia(media);
                    } else {
                        System.out.println("Media not found.");
                    }
                    updateStoreMenu();
                    break;
                case 0:
                    showMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    updateStoreMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            updateStoreMenu();
        }
    }

    public static void cartMenu() {
        cart.print();

        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Filter by: ");
                    System.out.println("1. ID");
                    System.out.println("2. Title");
                    int filterChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (filterChoice == 1) {
                        System.out.println("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        cart.searchById(id);
                    } else if (filterChoice == 2) {
                        System.out.println("Enter title: ");
                        String title = scanner.nextLine();
                        cart.searchByTitle(title);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    cartMenu();
                    break;
                case 2:
                    System.out.println("Sort by: ");
                    System.out.println("1. Title");
                    System.out.println("2. Cost");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (sortChoice == 1) {
                        cart.sortByTitleCost();
                    } else if (sortChoice == 2) {
                        cart.sortByCostTitle();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    cart.print();
                    cartMenu();
                    break;
                case 3:
                    System.out.println("Enter media title to remove: ");
                    String title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        cart.removeMedia(media);
                    } else {
                        System.out.println("Media not found in cart.");
                    }
                    cartMenu();
                    break;
                case 4:
                    System.out.println("Enter media title to play: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("Media cannot be played or not found.");
                    }
                    cartMenu();
                    break;
                case 5:
                    cart.placeOrder();
                    cartMenu();
                    break;
                case 0:
                    showMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    cartMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            cartMenu();
        }
    }
}
