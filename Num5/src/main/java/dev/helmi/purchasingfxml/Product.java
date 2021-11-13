package dev.helmi.purchasingfxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty stock;

    public Product(String id, String name, String stock) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.stock = new SimpleStringProperty(stock);
    }

    public String getId() {
        return id.get();
    }

    public String getStock() {
        return stock.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty stockProperty() {
        return stock;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }
}