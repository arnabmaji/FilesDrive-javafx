package io.github.arnabmaji19;

import io.github.arnabmaji19.model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final String APP_WINDOW = "log_in";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(APP_WINDOW), 350, 350);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();

        //connecting to database
        Database.getInstance().connect();
    }
}
