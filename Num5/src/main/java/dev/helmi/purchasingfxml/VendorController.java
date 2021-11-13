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

public class VendorController {
    @FXML
    private TableView tableVendor;
    @FXML
    private TableColumn clVendorId;
    @FXML
    private TableColumn clVendorName;
    @FXML
    private TableColumn clVendorAdd;
    @FXML
    private TextField tfVendorName;
    @FXML
    private TextField tfVendorAdd;
    private ObservableList<Vendor> data;

    @FXML
    private void initialize() {
        loadData();
    }

    public void loadData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM vendor";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                data.add(new Vendor(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            clVendorId.setCellValueFactory(new PropertyValueFactory("id"));
            clVendorName.setCellValueFactory(new PropertyValueFactory("name"));
            clVendorAdd.setCellValueFactory(new PropertyValueFactory("address"));

            tableVendor.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Loading Data");
        }
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        App.setRoot("main-view");
    }

    @FXML
    void onSaveButtonClick() {
        Connection c;
        String vendorName = tfVendorName.getText();
        String vendorAdd = tfVendorAdd.getText();
        try {
            c = DBConnect.connect();
            String SQL = "INSERT INTO `vendor` (`vendor_name`, `vendor_add`) VALUES ('" + vendorName + "', '" + vendorAdd + "')";
            int rs = c.createStatement().executeUpdate(SQL);
            if (rs > 0) {
                loadData();
                tfVendorName.setText("");
                tfVendorAdd.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Inserting Data");
        }
    }
}
