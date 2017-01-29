/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileguard;

import encryptdecrypt.Decrypt;
import encryptdecrypt.Encrypt;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author schoubey
 */
public class FXMLDocumentController implements Initializable {
    
    private String filePath;
    @FXML
    private Label label;
    
    @FXML
    private Button submitButton;
   
    @FXML
    private ChoiceBox choiceBox;
    
    @FXML
    private PasswordField passwordBox;
    
    private File openFileChooser(String s) {
        FileChooser fC = new FileChooser();
        fC.setTitle(s);
        fC.setInitialDirectory(new File(System.getProperty("user.home")));
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        File file = fC.showOpenDialog(stage);
        return file;
    }
    
    @FXML
    private void handlePickFile(ActionEvent event) {
        File file = openFileChooser("Select a file to encrypt or Decrypt");
        this.filePath = file.getPath();
    }
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        System.out.println("Clicked Submit button");
        String passkey = passwordBox.getText();
        System.out.println(passkey);
        if (passkey.length() < 6) {
            System.out.println("Password should be atleast 6 characters");
            return;
        }
        System.out.println(choiceBox.getValue());
        String operation = (String) choiceBox.getValue();
        switch (operation) {
            case "Encrypt": 
                Encrypt enc = new Encrypt(filePath, passkey);
                try {
                    enc.encryptFile();
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("Improper file encoding");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                File file = new File(filePath);
                Desktop encDesktop = Desktop.getDesktop();
                encDesktop.open(file.getParentFile());
                break;
            case "Decrypt":
                Decrypt dec = new Decrypt(filePath, passkey);
                try {
                    dec.decrypt();
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("Improper file encoding");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                File exfile = new File(filePath);
                Desktop decDesktop = Desktop.getDesktop();
                decDesktop.open(exfile.getParentFile());
                break; 
            default:
                System.out.println("What the fuck!");
        }
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.setItems(FXCollections.observableArrayList(
            "Encrypt", "Decrypt"
        ));
        choiceBox.setValue("Encrypt");
    }    
}
