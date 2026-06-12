package hust.soict.hedspi.aims.screen.manager;



import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.store.Store;

public class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JPanel centerPanel; // Khung để các lớp con gắn thêm ô nhập liệu
    protected JTextField tfTitle, tfCategory, tfCost;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Tái sử dụng lại thanh Menu (Sao chép logic từ StoreManagerScreen)
        cp.add(createNorth(), BorderLayout.NORTH);

        // Khung nhập liệu ở giữa
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2, 5, 5)); // Lưới 2 cột (Tên trường - Ô nhập)

        centerPanel.add(new JLabel("Title: ", SwingConstants.RIGHT));
        tfTitle = new JTextField(20);
        centerPanel.add(tfTitle);

        centerPanel.add(new JLabel("Category: ", SwingConstants.RIGHT));
        tfCategory = new JTextField(20);
        centerPanel.add(tfCategory);

        centerPanel.add(new JLabel("Cost: ", SwingConstants.RIGHT));
        tfCost = new JTextField(20);
        centerPanel.add(tfCost);

        cp.add(centerPanel, BorderLayout.CENTER);

        setTitle("Add Item");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // --- Các hàm tạo Menu giống hệt màn hình chính ---
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreMenu = new JMenuItem("View store");
        menu.add(viewStoreMenu);
        viewStoreMenu.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose();
        });

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookMenu = new JMenuItem("Add Book");
        JMenuItem addCDMenu = new JMenuItem("Add CD");
        JMenuItem addDVDMenu = new JMenuItem("Add DVD");

        addDVDMenu.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            dispose();
        });

        // THÊM 2 ĐOẠN NÀY
        addBookMenu.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            dispose();
        });

        addCDMenu.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            dispose();
        });
        smUpdateStore.add(addBookMenu);
        smUpdateStore.add(addCDMenu);
        smUpdateStore.add(addDVDMenu);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }
}
