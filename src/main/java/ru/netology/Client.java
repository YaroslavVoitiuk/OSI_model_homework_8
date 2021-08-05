package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String host = "netology.homework";
        int port = 8082;

        try (Socket clientSocket = new Socket(host,port);

            DataOutputStream printWriter = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream bufferedReader = new DataInputStream(clientSocket.getInputStream())) {

            System.out.println("## Connected to server\n## To kill the connection print close ");
            while (!clientSocket.isOutputShutdown()) {
                String inData = bufferedReader.readUTF();
                System.out.println("From server: " + inData );
                String outData = scanner.nextLine();
                if(outData.equalsIgnoreCase("close")){
                    System.out.println("## Client kill connections");
                    printWriter.writeUTF(outData);
                    break;
                }
                printWriter.writeUTF(outData);
                System.out.println("## sent to server");

            }
            System.out.println("## Client killed connection successfully");
        }

    }


}
