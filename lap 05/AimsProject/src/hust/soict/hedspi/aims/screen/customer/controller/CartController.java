package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
    private Cart cart;
    private Store store;
    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId; // Cột ID
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label costLabel;
    
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;

    // CHỈ CẦN 1 THAM SỐ LÀ CART (Đúng chuẩn Phần 6)
    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        // Cấu hình các cột
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        // Khởi tạo FilteredList để hỗ trợ lọc
        FilteredList<Media> filteredData = new FilteredList<>(FXCollections.observableArrayList(cart.getItemsOrdered()), p -> true);
        tblMedia.setItems(filteredData);

        // Lắng nghe sự kiện thanh tìm kiếm
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredData.setPredicate(media -> {
                    if (newValue == null || newValue.isEmpty()) return true; 
                    if (radioBtnFilterId.isSelected()) {
                    	try {
                    	    return media.getId() == Integer.parseInt(newValue);
                    	} catch (NumberFormatException e) {
                    	    return false;
                    	}
                    } 
                    else if (radioBtnFilterTitle.isSelected()) {
                        return media.getTitle().toLowerCase().contains(newValue.toLowerCase());
                    }
                    return true;
                });
            }
        });

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Bắt sự kiện chọn 1 dòng trong bảng
        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });
        
        costLabel.setText(cart.totalCost() + " $");
    }

    void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        FilteredList<Media> newFilteredData = new FilteredList<>(FXCollections.observableArrayList(cart.getItemsOrdered()), p -> true);
        tblMedia.setItems(newFilteredData);
        costLabel.setText(cart.totalCost() + " $");
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        // Lấy ra mặt hàng đang được chọn trong bảng
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText("Now playing:");
                alert.setContentText(media.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                System.err.println(e.getMessage());
                System.err.println(e.toString());
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText("Cannot play this media");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            
            // Truyền chung store và cart quay lại màn hình Store
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();
            
            // Lấy Stage hiện tại thông qua một Node bất kỳ trên màn hình (ở đây dùng tblMedia)
            Stage stage = (Stage) tblMedia.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        // Nếu giỏ hàng trống thì cảnh báo
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add some media before placing an order!");
            alert.showAndWait();
            return;
        }

        // Nếu có hàng thì báo đặt hàng thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Notification");
        alert.setHeaderText("Order Placed Successfully!");
        alert.setContentText("Your total is " + cart.totalCost() + " $\nThank you for shopping with AIMS!");
        alert.showAndWait();

        // Xóa sạch giỏ hàng sau khi đặt xong
        cart.getItemsOrdered().clear();
        
        // Cập nhật lại giao diện bảng và tổng tiền về 0
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsOrdered()));
        costLabel.setText("0 $");
    }
}
