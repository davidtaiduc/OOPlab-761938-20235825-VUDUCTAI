package hust.soict.hedspi.aims.store;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;

public class Store {

    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        itemsInStore.add(media);
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}