package ca.senecacollege.client;

import javafx.application.Platform;
import javafx.scene.layout.VBox;


import java.io.*;
import java.net.Socket;

/**
 * Client object for gui
 */
public class Client {
    public BufferedReader input;
    public PrintWriter output;
    public static Socket socket;
    public String message;

    static {
        try {
            socket = new Socket("localhost",6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for client object, uses socket to get streams.
     * @throws IOException - throws socket exception
     */
    public Client() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Receives messages from server
     * @param messages - FXML Element which will hold messages received
     */
    public void receiveFromServer(VBox messages) {
        new Thread(() -> {
            try {
                while (true) {
                    message = input.readLine();
                    if (!message.isEmpty()) {
                        Platform.runLater(() -> ClientApp.addMessage(message, messages));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
