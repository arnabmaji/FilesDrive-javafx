package io.github.arnabmaji19.controller;

import com.jfoenix.controls.JFXCheckBox;
import io.github.arnabmaji19.model.AlertDialog;
import io.github.arnabmaji19.model.Validations;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController {
    @FXML private StackPane stackPane;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private JFXCheckBox termsCheckBox;

    @FXML
    private void signUpNewUser(ActionEvent event){
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(username.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            confirmPassword.isBlank()){
            AlertDialog.show(stackPane, "Field can't be blank!");
            return;
        }

        if(!Validations.isEmailValid(email)){
            AlertDialog.show(stackPane, "Email is not valid!");
            return;
        }

        if(!password.equals(confirmPassword)){
            AlertDialog.show(stackPane, "Passwords do not match!");
            return;
        }

        if(!termsCheckBox.isSelected()){
            AlertDialog.show(stackPane, "Please agree to our terms and conditions");
        }

        //TODO: Check if email is already registered. or create new user

        AlertDialog.show(stackPane, "Successful!");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        var delay = new PauseTransition(Duration.seconds(2));
        //On success close this stage and show Log In stage
        delay.setOnFinished(event1 -> stage.close());
        delay.play();
    }
}
