package hust.soict.hedspi.aims.screen.manager;



import javax.swing.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector, tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store); // Gọi constructor của lớp cha để vẽ các thành phần chung
        setTitle("Add DVD to Store");

        // Thêm các ô nhập liệu riêng của DVD vào centerPanel của lớp cha
        centerPanel.add(new JLabel("Director: ", SwingConstants.RIGHT));
        tfDirector = new JTextField(20);
        centerPanel.add(tfDirector);

        centerPanel.add(new JLabel("Length: ", SwingConstants.RIGHT));
        tfLength = new JTextField(20);
        centerPanel.add(tfLength);

        // Nút bấm thực hiện thêm DVD
        JButton btnAdd = new JButton("Add DVD");
        btnAdd.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    try {
        	        String title = tfTitle.getText();
        	        String category = tfCategory.getText();
        	        float cost = Float.parseFloat(tfCost.getText());
        	        String director = tfDirector.getText();
        	        int length = Integer.parseInt(tfLength.getText());

        	        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
        	        store.addMedia(dvd);

        	        JOptionPane.showMessageDialog(null, "DVD added successfully!");

        	        tfTitle.setText("");
        	        tfCategory.setText("");
        	        tfCost.setText("");
        	        tfDirector.setText("");
        	        tfLength.setText("");
        	    } catch (NumberFormatException ex) {
        	        JOptionPane.showMessageDialog(null, "Cost and length must be valid numbers.");
        	    } catch (IllegalArgumentException ex) {
        	        JOptionPane.showMessageDialog(null, ex.getMessage());
        	    }
        	}
        });

        // Căn chỉnh giao diện: thêm 1 ô trống để nút Add bị đẩy sang cột bên phải
        centerPanel.add(new JLabel()); 
        centerPanel.add(btnAdd);

        setVisible(true); // Hiển thị màn hình
    }
}