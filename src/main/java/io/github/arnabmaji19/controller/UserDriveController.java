package io.github.arnabmaji19.controller;

import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.FileData;
import io.github.arnabmaji19.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserDriveController implements Initializable {

    @FXML private TilePane tilePane;

    private Database database;
    private Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.database = Database.getInstance();
        this.session = Session.getInstance();
    }

    @FXML
    private void uploadFileToDrive() throws IOException {
        File file = new FileChooser().showOpenDialog(new Stage());
        //If user has selected nothing
        if(file == null) return;

        double fileSize =  (double) file.length() /(1024 * 1024);
        String driveId = "drive_" + session.getDriveId();
        ObjectId fileId = database.uploadToDatabase(file);
        FileData fileData = new FileData(new ObjectId(), fileId, file.getName(),
                session.getUsername(), LocalDate.now().toString(), fileSize);
        database.getUserDrive(driveId).insertOne(fileData);
    }
}
