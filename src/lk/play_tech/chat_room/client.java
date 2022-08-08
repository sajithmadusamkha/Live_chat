package lk.play_tech.chat_room;

import java.io.*;
import java.net.Socket;

public class client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public client(Socket socket, String userName) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;
        } catch (IOException e) {
            close_everything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void close_everything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

    }
}
