package ca.senecacollege.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Class which controls an FX file
 */
public class ReceiveController implements Initializable {
    @FXML
    Button sendBtn;
    @FXML
    VBox messages;
    @FXML
    TextField msgField;
    @FXML
    ScrollPane scroll;
    private PrintWriter output;

    /**
     *  Like a constructor, works after FXML initialization
     * @param location - don't need in our case
     * @param resources - don't need in our case
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket socket = Client.socket;
            this.output = new PrintWriter(socket.getOutputStream(),true);
            messages.heightProperty().addListener((observableValue, number, t1) -> scroll.setVvalue((Double) t1));
            Client client = new Client();
            client.receiveFromServer(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Sends message from TextField on button click
     */
    @FXML
    void sendToServer() {
        String check = msgField.getText();
        if (!check.isEmpty()) {
            output.println(check);
            msgField.clear();
        }
    }
}
