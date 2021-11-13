package dev.helmi.purchasingfxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class ProductController {
    @FXML
    private TableView tableProduct;
    @FXML
    private TableColumn clProductId;
    @FXML
    private TableColumn clProductName;
    @FXML
    private TableColumn clProductStock;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductStock;

    private ObservableList<Product> data;

    @FXML
    private void initialize() {
        loadData();
    }

    public void loadData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM product";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                data.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            clProductId.setCellValueFactory(new PropertyValueFactory("id"));
            clProductName.setCellValueFactory(new PropertyValueFactory("name"));
            clProductStock.setCellValueFactory(new PropertyValueFactory("stock"));

            tableProduct.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        App.setRoot("main-view");
    }

    @FXML
    void onSaveButtonClick() {
        Connection c;
        String productName = tfProductName.getText();
        String productStock = tfProductStock.getText();
        try {
            c = DBConnect.connect();
            String SQL = "INSERT INTO `product` (`product_name`, `stock`) VALUES ('" + productName + "', '" + productStock + "')";
            int rs = c.createStatement().executeUpdate(SQL);
            if (rs > 0) {
                loadData();
                tfProductName.setText("");
                tfProductStock.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Inserting Data");
        }
    }
}
