module dev.helmi.purchasingfxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens dev.helmi.purchasingfxml to javafx.fxml;
    exports dev.helmi.purchasingfxml;
}