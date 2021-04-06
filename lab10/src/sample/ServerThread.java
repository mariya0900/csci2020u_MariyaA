package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ServerThread extends Thread{
    Socket clientSocket=null;
    ServerSocket serverSocket=null;
    protected PrintWriter out     = null;

    @FXML
    TextArea textArea;

    public ServerThread(Socket socket) throws IOException {
        super();
        clientSocket=socket;

    }
    public void run(){
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream.hasNextLine()){
            System.out.println(inputStream.nextLine());


           /*if (inputStream!=null){
                textArea.setText(inputStream.nextLine());
            }
            else{
                System.out.println("null");
            }*/

        }

        //closing clientSocket
        try {
            clientSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
