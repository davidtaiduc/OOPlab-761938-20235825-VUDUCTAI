package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.CompactDisc;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector, tfArtist;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD to Store");

        centerPanel.add(new JLabel("Director: ", SwingConstants.RIGHT));
        tfDirector = new JTextField(20);
        centerPanel.add(tfDirector);

        centerPanel.add(new JLabel("Artist: ", SwingConstants.RIGHT));
        tfArtist = new JTextField(20);
        centerPanel.add(tfArtist);

        JButton btnAdd = new JButton("Add CD");
        btnAdd.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    try {
        	        String title = tfTitle.getText();
        	        String category = tfCategory.getText();
        	        float cost = Float.parseFloat(tfCost.getText());
        	        String artist = tfArtist.getText();

        	        CompactDisc cd = new CompactDisc(title, category, artist, cost);
        	        store.addMedia(cd);

        	        JOptionPane.showMessageDialog(null, "CD added successfully!");

        	        tfTitle.setText("");
        	        tfCategory.setText("");
        	        tfCost.setText("");
        	        tfDirector.setText("");
        	        tfArtist.setText("");
        	    } catch (NumberFormatException ex) {
        	        JOptionPane.showMessageDialog(null, "Cost must be a valid number.");
        	    } catch (IllegalArgumentException ex) {
        	        JOptionPane.showMessageDialog(null, ex.getMessage());
        	    }
        	}
        });

        centerPanel.add(new JLabel()); 
        centerPanel.add(btnAdd);

        setVisible(true);
    }
}