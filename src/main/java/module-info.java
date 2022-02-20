module edu.uqtr.mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.uqtr.mvc to javafx.fxml;
    exports edu.uqtr.mvc;
    exports edu.uqtr.util;
    opens edu.uqtr.util to javafx.fxml;
}