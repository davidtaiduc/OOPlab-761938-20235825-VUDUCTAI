package bo;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("Invalid disc.");
            return;
        }
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered++] = disc;
            System.out.println("Added: " + disc.getTitle());
        } else {
            System.out.println("The cart is full. Cannot add more items.");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("Invalid disc.");
            return;
        }
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] != null && itemsOrdered[i].equals(disc)) { 
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[--qtyOrdered] = null;
                System.out.println("Removed: " + disc.getTitle());
                break;
            }
        }
        if (!found) {
            System.out.println("Disc not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] != null) {
                total += itemsOrdered[i].getCost();
            }
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n********** CART CONTENT **********");
        if (qtyOrdered == 0) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.printf("%-3s %-20s %10s\n", "No.", "Title", "Price ($)");
            for (int i = 0; i < qtyOrdered; i++) {
                System.out.printf("%-3d %-20s %10.2f\n", i + 1, itemsOrdered[i].getTitle(), itemsOrdered[i].getCost());
            }
            System.out.printf("\n%23s: %.2f $\n", "Total Cost", totalCost());
        }
        System.out.println("**********************************");
    }
}