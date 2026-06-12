package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML private Button btnAddToCart;
    @FXML private Button btnPlay;
    @FXML private Label lblTitle;
    @FXML private Label lblCost;

    private Media media;
    private Cart cart;

    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (cart != null) {
            cart.addMedia(media);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add to Cart");
            alert.setHeaderText(null);
            alert.setContentText("Added " + media.getTitle() + " to cart!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
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
}
