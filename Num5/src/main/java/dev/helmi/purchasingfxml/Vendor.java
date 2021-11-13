package dev.helmi.purchasingfxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vendor {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;

    public Vendor(String id, String name, String address) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
    }

    public String getId() {
        return id.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }
}