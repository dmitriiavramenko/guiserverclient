package ca.senecacollege.server;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class which controls an FX file
 */
public class ServerController implements Initializable {
    @FXML
    Pane mainPane;
    @FXML
    VBox messages;
    @FXML
    ScrollPane scroll;
    @Override
    /**
     *  Like a constructor, works after FXML initialization
     * @param location - don't need in our case
     * @param resources - don't need in our case
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messages.heightProperty().addListener((observableValue, number, t1) -> scroll.setVvalue((Double) t1));
        scroll.prefHeightProperty().bind(mainPane.heightProperty());
        scroll.prefWidthProperty().bind(mainPane.widthProperty());
        messages.prefHeightProperty().bind(scroll.heightProperty());
        messages.prefWidthProperty().bind(scroll.widthProperty());
        Server server = new Server();
        server.turn(messages);
    }
}
