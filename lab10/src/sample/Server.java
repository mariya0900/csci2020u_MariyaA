package sample;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    protected ServerThread[] threads    = null;
    protected int numClients                = 0;

    public static int MAX_CLIENTS = 5;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {

        try {
            serverSocket = new ServerSocket(8080);

            threads = new ServerThread[MAX_CLIENTS];
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client #"+(numClients+1)+" connected.");
                threads[numClients] = new ServerThread(clientSocket);
                threads[numClients].start();
                numClients++;


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



