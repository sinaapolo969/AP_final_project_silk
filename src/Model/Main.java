package Model;

import Network.Client;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application
{
    public static Socket socket;

    public static ArrayList<String> cities = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        PageControl.open("Home");
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        socket = client.setUp();
        cities();
        launch(args);
    }

    public static void cities()
    {
        cities.add("Chicago");
        cities.add("NewYork");
        cities.add("Washington");
        cities.add("Seattle");
        cities.add("San Francisco");
        cities.add("Miami");
        cities.add("Orlando");
    }
}
