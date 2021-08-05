package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        int port = 8082;

        try(ServerSocket serverSocket = new ServerSocket(port);) {

            Socket clientSocket = serverSocket.accept();
            System.out.println("## Connection accepted");
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            out.writeUTF(" Write your name ");
            String clientData = in.readUTF();
            out.writeUTF(" Are you child? (yes/no) ");
            String serverSplitter = in.readUTF().equalsIgnoreCase("yes") ?
                    "Welcome to the kids area," + clientData + "! Let`s play" :
                    "Welcome to the adult zone, " + clientData + "! Have a good rest, or a good working day!";
            out.writeUTF(serverSplitter);
            while (!clientSocket.isClosed()) {
                if (in.readUTF().equalsIgnoreCase("close")) {
                    System.out.println("## Client initialize connections abort");
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                }
            }
            System.out.println("## Connection was aborted successfully");


        }
    }
}
