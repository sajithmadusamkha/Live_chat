package lk.play_tech.chat_room.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;

    public static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtUserName.getText();
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/play_tech/chat_room/views/ClientFormOne.fxml"))));
        stage1.setTitle("Play Tech Pvt Ltd.");
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.show();
    }
}
