package io.github.arnabmaji19.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UserDriveController {

    @FXML private TilePane tilePane;

    @FXML
    private void uploadFileToDrive(){
        File file = new FileChooser().showOpenDialog(new Stage());
        //If user has selected nothing
        if(file == null) return;

        
    }
}
