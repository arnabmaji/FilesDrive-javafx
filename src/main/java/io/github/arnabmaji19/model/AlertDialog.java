package io.github.arnabmaji19.model;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AlertDialog {

    public static void show(StackPane stackPane, String text){
        var layout = new JFXDialogLayout();
        layout.setBody(new Text(text));
        new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.CENTER).show();
    }
}
