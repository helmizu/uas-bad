package dev.helmi.purchasingfxml;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TransactionController {
    @FXML
    private DatePicker iDate;
    @FXML
    private TextField iVendorId;
    @FXML
    private TextField iVendorRefNo;
    @FXML
    private TextField iVendorName;
    @FXML
    private ChoiceBox iProductId1, iProductId2, iProductId3;
    @FXML
    private TextField iProductName1, iProductName2, iProductName3;
    @FXML
    private TextField iQuantity1, iQuantity2, iQuantity3;
    @FXML
    private TextField iPrice1, iPrice2, iPrice3;
    @FXML
    private TextField iSubtotal1, iSubtotal2, iSubtotal3, iGrandTotal;
    private ObservableList<String> products;

    private String getProductNameById(String id) {
        Connection c;
        String name = "";
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM product WHERE product_id=" + id;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                name = rs.getNString("product_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getting Product Name Data");
        }
        return name;
    }

    private String getStockNameById(String id) {
        Connection c;
        String stock = "";
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM product WHERE product_id=" + id;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                stock = rs.getString("stock");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getting Product Stock Data");
        }
        return stock;
    }

    private String getVendorNameById(String id) {
        Connection c;
        String name = "";
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM vendor WHERE vendor_id=" + id;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                name = rs.getNString("vendor_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return name;
    }

    private void productValueListener(ChoiceBox box, TextField textField) {
        box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldIndex, Number newIndex) {
                String id = box.getItems().get((Integer) newIndex).toString();
                String name = getProductNameById(id);
                textField.setText(name);
            }
        });
    }

    private void vendorValueListener() {
        iVendorId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue != newValue) {
                    iVendorName.setText("");
                }
            }
        });
    }

    private void subtotalValueListener(TextField tfQty, TextField tfPrice, TextField tfSubtotal) {
        tfQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String sPrice = tfPrice.getText();
                String sQty = newValue;
                if (!sPrice.isEmpty() && !sQty.isEmpty()) {
                    if (oldValue != newValue) {
                        int qty = Integer.parseInt(sQty);
                        int price = Integer.parseInt(sPrice);
                        int subtotal = price * qty;
                        tfSubtotal.setText(String.valueOf(subtotal));
                    }
                } else {
                    tfSubtotal.setText("0");
                }
            }
        });
        tfPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String sPrice = newValue;
                String sQty = tfQty.getText();
                if (!sPrice.isEmpty() && !sQty.isEmpty()) {
                    if (oldValue != newValue) {
                        int qty = Integer.parseInt(sQty);
                        int price = Integer.parseInt(sPrice);
                        int subtotal = price * qty;
                        tfSubtotal.setText(String.valueOf(subtotal));
                    }
                } else {
                    tfSubtotal.setText("0");
                }
            }
        });
    }

    private void grandTotalValueListener(TextField tfSubtotal1, TextField tfSubtotal2, TextField tfSubtotal3) {
        tfSubtotal1.textProperty().addListener((observable, oldValue, newValue) -> {
            int subtotal1 = Integer.parseInt(newValue);
            int subtotal2 = Integer.parseInt(tfSubtotal2.getText());
            int subtotal3 = Integer.parseInt(tfSubtotal3.getText());
            iGrandTotal.setText(String.valueOf(subtotal1 + subtotal2 + subtotal3));
        });
        tfSubtotal2.textProperty().addListener((observable, oldValue, newValue) -> {
            int subtotal1 = Integer.parseInt(tfSubtotal1.getText());
            int subtotal2 = Integer.parseInt(newValue);
            int subtotal3 = Integer.parseInt(tfSubtotal3.getText());
            iGrandTotal.setText(String.valueOf(subtotal1 + subtotal2 + subtotal3));
        });
        tfSubtotal3.textProperty().addListener((observable, oldValue, newValue) -> {
            int subtotal1 = Integer.parseInt(tfSubtotal1.getText());
            int subtotal2 = Integer.parseInt(tfSubtotal2.getText());
            int subtotal3 = Integer.parseInt(newValue);
            iGrandTotal.setText(String.valueOf(subtotal1 + subtotal2 + subtotal3));
        });
    }

    private boolean validatePurchaseInfoValue(String date, String vendorId, String vendorRefNo) {
        boolean isValid = false;
        if (!date.isEmpty() && !vendorId.isEmpty() && !vendorRefNo.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean hasPurchaseDetailValue(String productId, String qty, String price) {
        boolean hasValue = false;
        if (!productId.isEmpty() || !qty.isEmpty() || !price.isEmpty()) {
            hasValue = true;
        }
        return hasValue;
    }

    private boolean validatePurchaseDetailValue(String productId, String qty, String price) {
        boolean isValid = false;
        if (!productId.isEmpty() && !qty.isEmpty() && !price.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

    private void loadProduct() {
        Connection c;
        products = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            String SQL = "SELECT * FROM product";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                products.add(rs.getString("product_id"));
            }

            iProductId1.setItems(products);
            iProductId2.setItems(products);
            iProductId3.setItems(products);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    private String getObjectValue(Object value) {
        if (value != null) return value.toString();
        return "";
    }

    private String insertPurchaseInfo(String date, String vendorId, String vendorRefNo) {
        Connection c;
        try {
            c = DBConnect.connect();
            String SQL = "INSERT INTO `purchase` (`purchase_date`, `vendor_id`, `vendor_refno`) VALUES (?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, date);
            ps.setString(2, vendorId);
            ps.setString(3, vendorRefNo);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next())
                    return generatedKeys.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Inserting Data");
        }
        return "";
    }

    private void insertBatchPurchaseDetail(String purchaseId, ArrayList<PurchaseDetail> details) {
        Connection c;
        try {
            c = DBConnect.connect();
            String SQL_INSERT = "INSERT INTO `purchase_detail`(`purchase_id`, `product_id`, `quantity`, `price`) VALUES (?, ?, ?, ?)";
            String SQL_UPDATE = "UPDATE product SET stock=? WHERE product_id=?";
            PreparedStatement psInsert = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psUpdate = c.prepareStatement(SQL_UPDATE);
            for (int i = 0; i < details.size(); i++) {
                PurchaseDetail detail = details.get(i);

                psInsert.setString(1, purchaseId);
                psInsert.setString(2, detail.getProductId());
                psInsert.setString(3, detail.getQuantity());
                psInsert.setString(4, detail.getPrice());
                psInsert.addBatch();
                psInsert.clearParameters();

                int newStock = Integer.parseInt(getStockNameById(detail.getProductId())) + Integer.parseInt(detail.getQuantity());
                psUpdate.setInt(1, newStock);
                psUpdate.setString(2, detail.getProductId());
                psUpdate.addBatch();
                psUpdate.clearParameters();
            }
            psInsert.executeBatch();
            psUpdate.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Inserting and Update Data");
        }
    }

    private void resetForm() {
        iDate.setValue(null);
        iVendorId.setText("");
        iVendorRefNo.setText("");
        iProductId1.setValue(null);
        iProductId2.setValue(null);
        iProductId3.setValue(null);
        iQuantity1.setText("");
        iQuantity2.setText("");
        iQuantity3.setText("");
        iPrice1.setText("");
        iPrice2.setText("");
        iPrice3.setText("");
    }
    
    @FXML
    protected void onSaveButtonClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        alert.setTitle("Error");

        String date = getObjectValue(iDate.getValue());
        String vendorId = iVendorId.getText();
        String vendorRefNo = iVendorRefNo.getText();
        String productId1 = getObjectValue(iProductId1.getValue()), productId2 = getObjectValue(iProductId2.getValue()), productId3 = getObjectValue(iProductId3.getValue());
        String qty1 = iQuantity1.getText(), qty2 = iQuantity2.getText(), qty3 = iQuantity3.getText();
        String price1 = iPrice1.getText(), price2 = iPrice2.getText(), price3 = iPrice3.getText();

        ArrayList<PurchaseDetail> details = new ArrayList<PurchaseDetail>();

        if (!validatePurchaseInfoValue(date, vendorId, vendorRefNo)) {
            alert.setContentText("Purchase info harus dilengkapi");
            alert.show();
            return;
        }

        boolean hasPurchaseDetail = hasPurchaseDetailValue(productId1, qty1, price1) && hasPurchaseDetailValue(productId2, qty2, price2) && hasPurchaseDetailValue(productId3, qty3, price3);
        if (hasPurchaseDetail) {
            alert.setContentText("Harus memiliki purchase detail minimal 1 product");
            alert.show();
            return;
        }

        if (hasPurchaseDetailValue(productId1, qty1, price1)) {
            if (!validatePurchaseDetailValue(productId1, qty1, price1)) {
                alert.setContentText("Purchase detail product 1 harus dilengkapi");
                alert.show();
                return;
            } else {
                details.add(new PurchaseDetail(productId1, qty1, price1));
            }
        }

        if (hasPurchaseDetailValue(productId2, qty2, price2)) {
            if (!validatePurchaseDetailValue(productId2, qty2, price2)) {
                alert.setContentText("Purchase detail product 2 harus dilengkapi");
                alert.show();
                return;
            } else {
                details.add(new PurchaseDetail(productId2, qty2, price2));
            }
        }

        if (hasPurchaseDetailValue(productId3, qty3, price3)) {
            if (!validatePurchaseDetailValue(productId3, qty3, price3)) {
                alert.setContentText("Purchase detail product 3 harus dilengkapi");
                alert.show();
                return;
            } else {
                details.add(new PurchaseDetail(productId3, qty3, price3));
            }
        }

        String purchaseId = insertPurchaseInfo(date, vendorId, vendorRefNo);
        if (purchaseId.isEmpty()) return;
        insertBatchPurchaseDetail(purchaseId, details);
        resetForm();
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        App.setRoot("main-view");
    }

    @FXML
    protected void onViewVendorName() throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        String vendorId = iVendorId.getText();
        if (!vendorId.isEmpty()) {
            String name = getVendorNameById(vendorId);
            if (!name.isEmpty()) {
                iVendorName.setText(name);
            } else {
                alert.setTitle("Error");
                alert.setContentText("Vendor belum terdaftar");
                alert.show();
            }
        } else {
            alert.setTitle("Error");
            alert.setContentText("Kode vendor harus diisi untuk melihat Nama Vendor");
            alert.show();
        }
    }

    @FXML
    private void initialize() {
        loadProduct();
        vendorValueListener();
        productValueListener(iProductId1, iProductName1);
        productValueListener(iProductId2, iProductName2);
        productValueListener(iProductId3, iProductName3);
        subtotalValueListener(iQuantity1, iPrice1, iSubtotal1);
        subtotalValueListener(iQuantity2, iPrice2, iSubtotal2);
        subtotalValueListener(iQuantity3, iPrice3, iSubtotal3);
        grandTotalValueListener(iSubtotal1, iSubtotal2, iSubtotal3);
    }
}
