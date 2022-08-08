package lk.play_tech.chat_room;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String user_name;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.user_name = bufferedReader.readLine();
            broadcast_massage("SERVER: " + user_name + "has entered the chat!");
        } catch (IOException e) {
            close_everything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String massage_from_client;

        while (socket.isConnected()){
            try {
                massage_from_client = bufferedReader.readLine();
                broadcast_massage(massage_from_client);
            } catch (IOException e) {
                close_everything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcast_massage(String massage) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if(!clientHandler.user_name.equals(user_name)){
                    clientHandler.bufferedWriter.write(massage);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                close_everything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void remove_client_handler(){
        clientHandlers.remove(this);
        broadcast_massage("SERVER: " + user_name + "has left the chat!");
    }

    private void close_everything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        remove_client_handler();
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
