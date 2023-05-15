module ru.naron.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens ru.naron.chat.application.impl to javafx.fxml;
    exports ru.naron.chat.application.impl;
}