package Model;

import Controller.HomeController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PageControl
{
    private static double x = 0;
    private static double y = 0;

    private static Stage primaryStage = new Stage();

    public static void open(String name) throws IOException
    {
        Parent root = FXMLLoader.load(PageControl.class.getResource("/View/" + name + ".fxml"));

        //the ability to move our stage
        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            }
        });

        //setting the scene to stage , deleting the line above our stage and showing it
        try
        {
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        }
        catch(Exception e)
        {

        }
    }

    public static FXMLLoader loader(String name)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HomeController.class.getResource("/View/" + name + ".fxml"));
        return loader;
    }
}
