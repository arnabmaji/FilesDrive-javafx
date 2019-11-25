package io.github.arnabmaji19.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

class FileTileController {
    @FXML private Text fileNameText;


    void initData(String text){
        fileNameText.setText(text);
    }
}
