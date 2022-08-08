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

    public void initialize(){
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            ServerFormController server = new ServerFormController(serverSocket);
            server.start_server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start_server(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                txt_area.appendText("A new client has connected!");
                ClientHandler client_handler = new ClientHandler(socket);

                Thread thread = new Thread(client_handler);
                thread.start();
            }
        } catch (IOException e) {

        }
    }

    public void close_server_socket(){
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
