package lk.play_tech.chat_room.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.play_tech.chat_room.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {

    public ImageView img_minimize;
    public JFXTextArea txt_area;

    private ServerSocket serverSocket;

    public ServerFormController(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void initialize() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        ServerFormController server = new ServerFormController(serverSocket);
        server.startServer();
    }

    public void startServer(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                txt_area.appendText("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void img_close_on_clicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @FXML
    public void img_minimize_on_clicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) img_minimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
