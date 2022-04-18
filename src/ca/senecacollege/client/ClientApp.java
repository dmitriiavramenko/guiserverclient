package ca.senecacollege.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Class which starts FX Logic for Client
 */
public class ClientApp extends Application {
    /**
     *  Starts FX
     * @param stage - main stage (window) of our GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Client");
            Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.sizeToScene();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * "Starts" the start, take arguments from console
     * @param args - console arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Takes FX element from FXML file and add message to it
     * @param msg - message we want to add to FX
     * @param messages - pane (VBox) which holds all messages
     */
    public static void addMessage(String msg, VBox messages) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);
        messages.getChildren().add(hBox);
    }
}
