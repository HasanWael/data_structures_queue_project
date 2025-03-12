module com.bankqueue {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.bankqueue.controller to javafx.fxml;
    exports com.bankqueue;
}