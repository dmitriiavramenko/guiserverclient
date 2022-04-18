package ca.senecacollege.server;

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


public class ServerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
            primaryStage.setTitle("Server");
            Scene scene1 = new Scene(root);
            primaryStage.setScene(scene1);
            primaryStage.sizeToScene();
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



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
