package io.github.arnabmaji19.controller;

import io.github.arnabmaji19.model.FileData;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FileDetailsController{
    @FXML private Text fileNameText;
    @FXML private Text ownerText;
    @FXML private Text uploadDateText;
    @FXML private Text sizeText;

    void initData(FileData fileData){
        fileNameText.setText(fileData.getFileName());
        ownerText.setText(fileData.getOwner());
        uploadDateText.setText(fileData.getUploadDate());
        sizeText.setText(String.format("%.6f", fileData.getSizeInMB()) + "MB");
    }
}
