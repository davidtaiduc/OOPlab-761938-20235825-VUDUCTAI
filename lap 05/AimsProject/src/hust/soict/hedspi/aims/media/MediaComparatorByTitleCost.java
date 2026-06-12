package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        if (m1 == null && m2 == null) {
            return 0;
        }
        if (m1 == null) {
            return -1;
        }
        if (m2 == null) {
            return 1;
        }

        String title1 = m1.getTitle();
        String title2 = m2.getTitle();

        if (title1 == null && title2 == null) {
            return Float.compare(m2.getCost(), m1.getCost());
        }
        if (title1 == null) {
            return -1;
        }
        if (title2 == null) {
            return 1;
        }

        int titleCompare = title1.compareToIgnoreCase(title2);
        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(m2.getCost(), m1.getCost());
    }
}
