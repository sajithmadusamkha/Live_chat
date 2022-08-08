package lk.play_tech.chat_room.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class ClientFormOneController {
    public ImageView img_minimize;
    public JFXTextArea txt_area;
    public TextField txtClientMassage;
    public ImageView imgSendImages;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public ClientFormOneController(Socket socket) {
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

    @FXML
    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}
