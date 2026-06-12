package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel {

    public MediaStore(Media media) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel title = new JLabel(media.getTitle());
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JButton playButton = null;

        if (media instanceof Playable) {
            playButton = new JButton("Play");
            playButton.setAlignmentX(CENTER_ALIGNMENT);

            playButton.addActionListener(e -> {
                ((Playable) media).play();
            });
        }

        add(Box.createVerticalGlue());
        add(title);
        add(cost);

        if (playButton != null) {
            add(playButton);
        }

        add(Box.createVerticalGlue());
    }
}