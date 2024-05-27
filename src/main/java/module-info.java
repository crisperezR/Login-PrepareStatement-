module org.example.u2p7_logincss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.xerial.sqlitejdbc;

    opens org.example.u2p7_logincss to javafx.fxml;
    exports org.example.u2p7_logincss;
}