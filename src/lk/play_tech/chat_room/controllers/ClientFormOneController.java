package lk.play_tech.chat_room.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientFormOneController extends Thread {
    public JFXTextArea txt_area;
    public TextField txtClientMassage;
    public ImageView imgSendImages;
    public Label lblUserName;

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;

    public void initialize() {
        String userName = LoginFormController.userName;
        lblUserName.setText(userName);

        try {
            socket = new Socket("localhost", 5000);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while (true) {
                String massage = bufferedReader.readLine();
                String[] tokens = massage.split(" ");
                String command = tokens[0];
                System.out.println(command);
                StringBuilder clientMassage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    clientMassage.append(tokens[i]);
                }
                System.out.println(clientMassage);
                //System.out.println("command= " + command + "  User name= " + lblUserName.getText());
                if(!command.equalsIgnoreCase(lblUserName.getText()+": ")) {
                    txt_area.appendText(massage + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        String massage = txtClientMassage.getText();
        printWriter.println(lblUserName.getText() + ": " + massage);
        txt_area.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txt_area.appendText("Me: " + massage + "\n");
        txtClientMassage.clear();
        printWriter.flush();
        if(massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtClientMassage.getScene().getWindow();
            stage.close();
        }
    }

}
