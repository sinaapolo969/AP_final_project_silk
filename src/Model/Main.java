package Model;

import Network.Client;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;

public class Main extends Application
{
    private static Socket socket;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        PageControl.open("Home");
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        socket = client.setUp();
        launch(args);
    }
}
