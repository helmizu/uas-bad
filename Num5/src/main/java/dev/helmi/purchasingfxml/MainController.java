package dev.helmi.purchasingfxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onVendorButtonClick() throws IOException {
        App.setRoot("vendor-view");
    }

    @FXML
    protected void onProductButtonClick() throws IOException {
        App.setRoot("product-view");
    }

    @FXML
    protected void onPembelianButtonClick() throws IOException {
        App.setRoot("transaction-view");
    }

    @FXML
    protected void onExitButtonClick() {
        App.exitApp();
    }
}