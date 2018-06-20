package components;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Controller {

    @FXML
    private TextArea field1;

    @FXML
    private TextArea field2;

    @FXML
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Custom Files", "html"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int userSelection = fileChooser.showSaveDialog(new JFrame());

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                String fileContents = "";
                while ((st = br.readLine()) != null) {
                    fileContents += st;
                }

                field1.setText(fileContents);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("File Open Failed");
                alert.setContentText("Your file has failed to open. Please try again.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void saveFile() {
        // Check to make sure a conversion is done before we download the contents into a file.
        if (field2.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning...");
            alert.setHeaderText("No file to be saved");
            alert.setContentText("Please do a code conversion before trying to save the file. Please enter Java skeleton code to be converted, then press the convert button.");
            alert.showAndWait();
        } else {
            // Save the contents of field2 to a file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Java File", "java"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int userSelection = fileChooser.showSaveDialog(new JFrame());

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    FileOutputStream out = new FileOutputStream(path + ((path.contains(".java")) ? "" : ".java"));
                    out.write(field2.getText().getBytes());
                    out.close();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Saved");
                    alert.setHeaderText("File Saved Successfully");
                    alert.setContentText("Your file has been successfully saved to " + path + ((path.contains(".java")) ? "" : ".java"));
                    alert.showAndWait();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("File Save Failed");
                    alert.setContentText("Your file has failed to save. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void convert() {
        System.out.println("convert clicked");
        String originalText = field1.getText();

        if (originalText.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning...");
            alert.setHeaderText("No code to be converted");
            alert.setContentText("The Java code to be converted field is empty. Please enter Java skeleton code to be converted, then press the convert button.");
            alert.showAndWait();
        } else {
            // Start parsing the Java Skeleton Code and make it real code.

            //Finally, output the real code to the text box on the right.
            field2.setText("Conversion Done.");
        }
    }
}
