module com.mycompany.qlthuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.qlthuvien to javafx.fxml;
    exports com.mycompany.qlthuvien;
}
