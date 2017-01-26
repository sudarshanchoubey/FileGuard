/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileguard;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author schoubey
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private ChoiceBox choiceBox;
    
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        System.out.println(choiceBox.getValue());
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        if (label.getText().contentEquals("Hello World!")) {
            label.setText("");
        } else {
            label.setText("Hello World!");
        }
    }
    
    private static File openFileChooser(String s) {
        FileChooser fC = new FileChooser();
        fC.setTitle(s);
        fC.setInitialDirectory(new File("C:\\Users\\schoubey\\Documents\\Coding\\java_coding\\CharacterRecognition\\res"));
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        File file = fC.showOpenDialog(stage);
        return file;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
