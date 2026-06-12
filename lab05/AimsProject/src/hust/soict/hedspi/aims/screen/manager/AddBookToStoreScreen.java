package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Book;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");

        centerPanel.add(new JLabel("Authors (comma separated): ", SwingConstants.RIGHT));
        tfAuthors = new JTextField(20);
        centerPanel.add(tfAuthors);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    try {
        	        String title = tfTitle.getText();
        	        String category = tfCategory.getText();
        	        float cost = Float.parseFloat(tfCost.getText());
        	        String authors = tfAuthors.getText();

        	        Book book = new Book(title, category, cost);

        	        if (!authors.trim().isEmpty()) {
        	            String[] authorList = authors.split(",");
        	            for (String author : authorList) {
        	                book.addAuthor(author.trim());
        	            }
        	        }

        	        store.addMedia(book);
        	        JOptionPane.showMessageDialog(null, "Book added successfully!");

        	        tfTitle.setText("");
        	        tfCategory.setText("");
        	        tfCost.setText("");
        	        tfAuthors.setText("");
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