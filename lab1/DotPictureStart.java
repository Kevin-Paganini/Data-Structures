package paganiniK;
/*
 *  Course: CS1011-051
 *  Fall 2020-2021
 *  File header contains class DotPictureStart
 *  Name: paganinik
 *  Created 3/22/2021
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Course CS1011-051
 * Fall 2020-2021
 * DotPictureStart purpose: launch the gui
 *
 * @author paganinik
 * @version created on 3/22/2021 at 8:36 PM
 */

public class DotPictureStart extends Application {
    /**
     * Start method of the JavaFX program
     * @param stage the stage
     * @throws IOException if there is a problem loading Dot2Dot.fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DotPictureGUI.fxml"));
        stage.setTitle("Dot to Dot");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

