package io.github.arnabmaji19.controller;

import io.github.arnabmaji19.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private void signUpNewUser(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try{
            Parent root = App.loadFXML("sign_up");
            stage.setScene(new Scene(root, 350, 450));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
