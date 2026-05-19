package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame {

    private Store store;
    private JPanel centerPanel;

    public StoreManagerScreen(Store store) {
        this.store = store;

        setTitle("AIMS Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(createNorth(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
    }

    // ===== TOP =====
    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        north.add(createMenuBar());
        north.add(createHeader());

        return north;
    }

    private JMenuBar createMenuBar() {

        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View Store");

        JMenu updateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");

        // ===== ACTIONS =====
        addDVD.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add DVD clicked");
        });

        updateStore.add(addBook);
        updateStore.add(addCD);
        updateStore.add(addDVD);

        menu.add(viewStore);
        menu.add(updateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();

        JLabel title = new JLabel("AIMS STORE");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.BLUE);

        header.add(title);

        return header;
    }

    // ===== CENTER =====
    private JPanel createCenter() {

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 3, 10, 10));

        ArrayList<Media> mediaList = store.getItemsInStore();

        for (Media m : mediaList) {
            centerPanel.add(new MediaStore(m));
        }

        JScrollPane scroll = new JScrollPane(centerPanel);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scroll, BorderLayout.CENTER);

        return wrapper;
    }

    // ===== REFRESH UI =====
    public void refresh() {
        centerPanel.removeAll();

        for (Media m : store.getItemsInStore()) {
            centerPanel.add(new MediaStore(m));
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }
}