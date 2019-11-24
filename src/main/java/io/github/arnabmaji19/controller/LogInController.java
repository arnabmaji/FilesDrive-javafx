package io.github.arnabmaji19.controller;

import com.mongodb.client.model.Filters;
import io.github.arnabmaji19.App;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.Session;
import io.github.arnabmaji19.model.User;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LogInController {

    @FXML private StackPane stackPane;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    private void logInExistingUser(){
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = Database.getInstance()
                .getUsersCollection()
                .find(Filters.eq("email", email)).first();
        if (user == null){
            AlertDialog.show(stackPane, "Email not registered!");
            return;
        }
        if(!user.getPassword().equals(password)){
            AlertDialog.show(stackPane, "Password invalid!");
            return;
        }

        //Log In successful
        AlertDialog.show(stackPane, "Log In successful!");
        //Creating session for current user
        Session.getInstance().create(user);

        var delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            Stage primaryStage = (Stage) stackPane.getScene().getWindow();
            primaryStage.close();
            try{
                Parent root = App.loadFXML("user_drive");
                primaryStage.setScene(new Scene(root, 1100, 600));
                primaryStage.show();
            } catch (IOException e){

            }
        });
        delay.play();
    }

    @FXML
    private void signUpNewUser(){
        //Display the Sign Up window
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
