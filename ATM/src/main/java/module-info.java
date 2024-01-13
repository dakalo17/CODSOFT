module com.codsoft.atm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.codsoft.atm to javafx.fxml;
    exports com.codsoft.atm;
}