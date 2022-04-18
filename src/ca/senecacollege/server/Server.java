package ca.senecacollege.server;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Server object for gui
 */
public class Server {

    public LinkedList<Socket> sockets = new LinkedList<>();

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;

    /**
     * Turns server including all threads
     * @param pane - FXML Element which will hold messages received
     */
    public void turn(VBox pane) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(6000);
                while (true) {
                    Platform.runLater(() -> ServerApp.addMessage("Waiting for the client...", pane));
                    socket = serverSocket.accept();
                    sockets.add(socket);
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String userData = input.readLine();
                    Platform.runLater(() -> ServerApp.addMessage("Connection established! Client nickname: " + userData, pane));
                    sendMsgs(pane, userData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Send new received messages from clients to other clients
     * @param pane - FXML Element which will hold messages received
     * @param nickname - nickname of the client
     */
    public void sendMsgs(VBox pane, String nickname) {
            new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while(true) {
                        String userData = input.readLine();
                        for (Socket socket : sockets) {
                            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                            output.println(nickname + ": " + userData);
                            output.flush();
                        }
                        Platform.runLater(() -> ServerApp.addMessage(nickname + ": " + userData, pane));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
}
