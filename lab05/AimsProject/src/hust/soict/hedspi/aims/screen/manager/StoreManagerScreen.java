package hust.soict.hedspi.aims.screen.manager;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Book;

public class StoreManagerScreen extends JFrame {
    private Store store;

    // --- XÂY DỰNG KHU VỰC PHÍA BẮC (Thanh Menu + Header) ---
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
        // Nút View Store: Mở lại màn hình chính
        viewStoreMenu.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose(); // Đóng cửa sổ hiện hành
        });
        
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookMenu = new JMenuItem("Add Book");
        JMenuItem addCDMenu = new JMenuItem("Add CD");
        JMenuItem addDVDMenu = new JMenuItem("Add DVD");
        
        // Nút Add DVD: Mở màn hình thêm DVD
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
        // Bạn sẽ gắn sự kiện cho Add Book và Add CD sau khi tạo class
        
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

    // --- XÂY DỰNG KHU VỰC TRUNG TÂM (Lưới sản phẩm) ---
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        // Lấy danh sách sản phẩm và in ra thành từng ô
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    // --- CONSTRUCTOR GẮN KẾT MỌI THỨ ---
    public StoreManagerScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // --- HÀM MAIN ĐỂ CHẠY THỬ ---
    public static void main(String[] args) {
        Store store = new Store();
        
        // Thêm vài dữ liệu mẫu để giao diện có cái hiển thị
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladin", "Animation", 18.99f));
        store.addMedia(new Book("Java Programming", "Education", 25.50f));
        store.addMedia(new CompactDisc("Greatest Hits", "Music", "Various", 20.00f));
        
        // Mở màn hình
        new StoreManagerScreen(store);
    }
}