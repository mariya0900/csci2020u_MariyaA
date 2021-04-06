package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button exitButton;




    public void sendMessage(ActionEvent actionEvent) throws IOException {

        String message=txtMessage.getText();
        String username=txtUsername.getText();

        Socket clientSocket=null;
        PrintWriter out=null;
        try{
            clientSocket=new Socket("localhost", 8080);
            out=new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
            out.print(username+":");
            out.println(message);
            out.flush();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            out.close();
        }


        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("serverWindow.fxml"));
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        Scene serverScene = new Scene(loader.load());
        stage.setScene(serverScene);*/

    }

    public void exit(ActionEvent actionEvent) {

        Platform.exit();
    }

    public void exitToClient(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Scene clientScene = new Scene(loader.load());
        stage.setScene(clientScene);

        //Platform.exit();

    }
}
