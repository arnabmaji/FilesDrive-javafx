package io.github.arnabmaji19.controller;

import com.mongodb.client.model.Filters;
import io.github.arnabmaji19.App;
import io.github.arnabmaji19.model.Database;
import io.github.arnabmaji19.model.FileData;
import io.github.arnabmaji19.model.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.TilePane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserDriveController implements Initializable {

    @FXML private TilePane tilePane;

    private Database database;
    private Session session;
    private ObservableList<FileData> userFilesList;
    private String driveId;
    private Stage secondaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.database = Database.getInstance();
        this.session = Session.getInstance();
        this.driveId = "drive_" + session.getDriveId();
        //Setting up secondary stage for File and Directory chooser
        secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);

        userFilesList = FXCollections.observableArrayList();
        for(var fileData : database.getUserDrive(driveId).find()){
            userFilesList.add(fileData);
        }
        try {
            displayUserFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void uploadFileToDrive() throws IOException {
        File file = new FileChooser().showOpenDialog(secondaryStage);
        //If user has selected nothing
        if(file == null) return;

        double fileSize =  (double) file.length() /(1024 * 1024);
        ObjectId fileId = database.uploadToDatabase(file);
        var fileData = new FileData(new ObjectId(), fileId, file.getName(),
                session.getUsername(), LocalDate.now().toString(), fileSize);
        database.getUserDrive(driveId).insertOne(fileData);
        addFileToList(fileData);
    }

    private void displayUserFiles() throws IOException {
        for(var fileData : userFilesList){
            addFileToList(fileData);
        }
    }

    private void addFileToList(FileData fileData) throws IOException {
        var loader  = App.getFXMLLoader("file_tile");
        var controller = new FileTileController();
        loader.setController(controller);
        Parent root = loader.load();
        controller.initData(fileData.getFileName());

        //Creating context menu
        var menu = new ContextMenu();
        var itemSave = new MenuItem("Save");
        var itemRemove = new MenuItem("Remove");
        var itemDetails = new MenuItem("Details");
        //View File details
        itemDetails.setOnAction(event -> {
            try {
                FXMLLoader fileDetailsLoader = App.getFXMLLoader("file_details_dialog");
                Parent fileDetailsRoot = fileDetailsLoader.load();
                FileDetailsDialogController fileDetailsDialogController = fileDetailsLoader.getController();
                fileDetailsDialogController.initData(fileData);
                Stage stage = new Stage();
                stage.setScene(new Scene(fileDetailsRoot));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Remove file from drive
        itemRemove.setOnAction(event -> {
            //Show confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure want to remove file?");
            alert.setContentText(fileData.getFileName());

            Optional<ButtonType> option = alert.showAndWait();

            //If the user has selected nothing or cancels
            if(option.isEmpty() || option.get() == ButtonType.CANCEL){
                return;
            }

            tilePane.getChildren().remove(root);
            //Remove file from database and user's drive
            new Thread(() -> {
                database.getUserDrive(driveId)
                        .deleteOne(Filters.eq("_id", fileData.getId()));
                database.deleteFile(fileData.getFileId());
            }).start();
        });
        //Save file to local pc
        itemSave.setOnAction(event -> {
            File file = new DirectoryChooser().showDialog(secondaryStage);
            if(file == null){             //User hasn't selected any folder
                return;
            }
            String path = file.getAbsolutePath() + "/" + fileData.getFileName();
            try {
                database.downloadFromDatabase(fileData.getFileId(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        menu.getItems().addAll(itemSave, itemRemove, itemDetails);
        root.setOnContextMenuRequested(event -> menu.show(root, event.getScreenX(),
                event.getScreenY()));
        tilePane.getChildren().add(root);
    }

    @FXML
    private void logOutCurrentUser() throws IOException {
        Stage primaryStage = (Stage) tilePane.getScene().getWindow();
        primaryStage.close();
        primaryStage.setScene(new Scene(App.loadFXML("log_in"), 350, 350));
        session.clear();
        primaryStage.show();
    }

    @FXML
    private void showAboutDialog() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(App.loadFXML("about_dialog"), 400, 500));
        stage.showAndWait();
    }
}
