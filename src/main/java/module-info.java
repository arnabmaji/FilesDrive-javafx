module io.github.arnabmaji19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    opens io.github.arnabmaji19 to javafx.fxml;
    exports io.github.arnabmaji19;
    opens io.github.arnabmaji19.controller;
    opens io.github.arnabmaji19.fxml;
}
