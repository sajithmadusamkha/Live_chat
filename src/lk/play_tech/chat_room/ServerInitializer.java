package lk.play_tech.chat_room;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServerInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/play_tech/chat_room/views/ServerForm.fxml"));
        Scene mainScene = new Scene(parent);
        mainScene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Play Tech Server");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
