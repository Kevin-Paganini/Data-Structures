/*
 *  Course: CS1011-051
 *  Fall 2020-2021
 *  File header contains class DotPictureGUI
 *  Name: paganinik
 *  Created 3/22/2021
 */
package paganiniK;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.PatternSyntaxException;

/**
 * Course CS1011-051
 * Fall 2020-2021
 * DotPictureGUI purpose:
 *
 * @author paganinik
 * @version created on 3/22/2021 at 5:32 PM
 */
public class DotPictureGUIController {
    @FXML
    private Canvas canvas;

    @FXML
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Menu draw;

    @FXML
    private Label numDotsLabel;

    @FXML
    private MenuItem save;

    private Picture picture;

    private Picture origPic;

    /**
     * Helper method to clear the canvas
     * @param canvas
     */
    private void clearCanvas(Canvas canvas){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Updates num labels
     */
    private void updateNumLabels(){
        numDotsLabel.setText("Number of Dots: " + picture.getNumDots());
    }


    /**
     * Opens a dot file
     * @param actionEvent
     */
    public void open(ActionEvent actionEvent) {
        //FileChooser initialisation
        FileChooser fc = new FileChooser();
        File fileDir = new File("data");
        fc.setInitialDirectory(fileDir);
        fc.setTitle("file chooser");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dot files", "*.dot"));

        File file = fc.showOpenDialog(null);
        if(file != null){
            try{

                Path path = file.toPath();
                origPic = new Picture(new LinkedList<Dot>());
                origPic.load(path);
                picture = origPic;
                clearCanvas(canvas);
                picture.drawDots(canvas);
                picture.drawLines(canvas);
                draw.setDisable(false);
                save.setDisable(false);
                updateNumLabels();

            } catch(NumberFormatException e){
                System.out.println("number format error");
                alert.setTitle("ERROR");
                alert.setContentText("Number format error");
                alert.showAndWait();


            } catch (IOException e) {
                System.out.println("Input / Output error");
                alert.setTitle("ERROR");
                alert.setContentText("Input / Output error");
                alert.showAndWait();
            }
        }


    }

    /**
     * Closes the GUI
     * @param actionEvent
     */
    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * saves a .dot file
     * @param actionEvent
     */
    public void save(ActionEvent actionEvent) {
        //FileChooser initialisation
        FileChooser fc = new FileChooser();
        File fileDir = new File("data");
        fc.setInitialDirectory(fileDir);
        fc.setTitle("Save file");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dot files", "*.dot"));
        File file = fc.showSaveDialog(null);

        if(file != null){

            try{
                Path path = file.toPath();
                picture.save(path);


            } catch (IOException e) {
                alert.setTitle("Trouble saving file...");
                alert.setContentText("Sorry the picture could not be saved.");
                alert.showAndWait();

            }

        }
    }

    /**
     * Draws only lines
     * @param actionEvent
     */
    public void drawOnlyLines(ActionEvent actionEvent) {
        try {
            clearCanvas(canvas);
            picture.drawLines(canvas);

        } catch(NullPointerException e){
            System.out.println("Error drawing lines");
            alert.setContentText("Error drawing lines");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

    /**
     * Draws only the dots
     * @param actionEvent
     */
    public void drawOnlyDots(ActionEvent actionEvent) {
        try {
            clearCanvas(canvas);
            picture.drawDots(canvas);

        } catch(NullPointerException e){
            System.out.println("Error drawing dots");
            alert.setContentText("Error drawing dots");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

    /**
     * Changes the number of dots in the picture
     * @param actionEvent
     */
    public void changeNumDots(ActionEvent actionEvent) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Number of Dots");
            dialog.setContentText("Number of Dots: ");
            dialog.setHeaderText("How many dots would you like");
            Optional<String> input = dialog.showAndWait();
            String UInumDots = input.get();

            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Strategy");
            dialog2.setContentText("Strategy" + ": Index or Iterator");
            dialog2.setHeaderText("What strategy would you like");
            Optional<String> input2 = dialog2.showAndWait();
            String UIStrategy = input2.get();


            int numDots = Integer.parseInt(UInumDots);
            picture = new Picture(origPic, new LinkedList<Dot>());
            picture.removeDots(numDots, UIStrategy);

            clearCanvas(canvas);
            picture.drawLines(canvas);
            picture.drawDots(canvas);
            updateNumLabels();

        } catch(NoSuchElementException e){
            alert.setContentText("You have to enter something please...");
            alert.setTitle("Error");
            alert.showAndWait();
        } catch(PatternSyntaxException | ArrayIndexOutOfBoundsException e){
            alert.setContentText("Error in input..." +
                    " Write number of dots space and then the method (Index or Iterator)");
            alert.setTitle("Error in user input. Please try again.");
            alert.showAndWait();
        } catch(NullPointerException | NumberFormatException e){
            System.out.println("Error in number of dots or formatting");
            alert.setContentText("Error drawing dots");
            alert.setTitle("Error");
            alert.showAndWait();
        }

    }
}

