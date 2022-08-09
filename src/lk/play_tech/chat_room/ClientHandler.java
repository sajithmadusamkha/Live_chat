package lk.play_tech.chat_room;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientUserName;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.clientUserName = dataInputStream.readUTF();
            clientHandlers.add(this);
            broadCastMassage("Server: " + clientUserName +"has entered the chat!");
        } catch (IOException e) {
            closEverything(socket, dataInputStream, dataOutputStream);
        }
    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                massageFromClient = dataInputStream.readUTF();
                broadCastMassage(massageFromClient);
            } catch (IOException e) {
                closEverything(socket, dataInputStream, dataOutputStream);
                break;
            }
        }
    }

    public void broadCastMassage(String massageToSend) {
        for(ClientHandler clientHandler : clientHandlers) {
            try{
                if(!clientHandler.clientUserName.equals(clientUserName)) {
                    clientHandler.dataOutputStream.writeUTF("\n" + massageToSend);
                    clientHandler.dataOutputStream.flush();
                }
            } catch (IOException e) {
                closEverything(socket, dataInputStream, dataOutputStream);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadCastMassage("Server:" + clientUserName + "has left the chat!");
    }

    private void closEverything(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        removeClientHandler();
        try {
            if(dataInputStream != null) {
                dataInputStream.close();
            }

            if (dataOutputStream != null) {
                dataOutputStream.close();
            }

            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
