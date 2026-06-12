package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;

public class Aims {

    public static void main(String[] args) {

        Store store = new Store();

        store.addMedia(new DigitalVideoDisc(1,"Lion King","Animation",19.95f,87,"Roger Allers"));
        store.addMedia(new DigitalVideoDisc(2,"Interstellar","Sci-Fi",25.5f,169,"Nolan"));
        store.addMedia(new DigitalVideoDisc(3,"Avengers","Action",30f,181,"Russo"));
        store.addMedia(new DigitalVideoDisc(4,"Inception","Sci-Fi",22f,148,"Nolan"));
        store.addMedia(new DigitalVideoDisc(5,"Titanic","Romance",18f,195,"Cameron"));
        store.addMedia(new DigitalVideoDisc(6,"Matrix","Sci-Fi",20f,136,"Wachowski"));
        store.addMedia(new DigitalVideoDisc(7,"Frozen","Animation",15f,102,"Disney"));
        store.addMedia(new DigitalVideoDisc(8,"Joker","Drama",21f,122,"Phillips"));
        store.addMedia(new DigitalVideoDisc(9,"Batman","Action",23f,140,"Nolan"));

        new StoreManagerScreen(store);
    }
}