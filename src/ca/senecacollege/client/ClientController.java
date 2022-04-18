package ca.senecacollege.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class which controls an FX file
 */
public class ClientController implements Initializable {

    @FXML
    private TextField nicknameField;


    private String sendData;
    private String nickname;
    private Socket socket;
    private PrintWriter output;

    /**
     *  Like a constructor, works after FXML initialization
     * @param location - don't need in our case
     * @param resources - don't need in our case
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.socket = Client.socket;
            this.output = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *  On button click from FXML it loads chat scene
     * @param event - event happening
     */
    @FXML
    public void switchToScene2(ActionEvent event) {
        try {
            sendData = nicknameField.getText();
            this.nickname = sendData;
            output.println(sendData);

            Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(nickname);
            stage.sizeToScene();
            stage.show();

        }catch(IOException ex) {
            System.out.println("Client Exception: " + ex.getMessage());
        }
    }







}
