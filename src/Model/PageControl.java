package Model;

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

    public static void open(String name) throws IOException
    {
        Parent root = FXMLLoader.load(PageControl.class.getResource("/view/" + name + ".fxml"));
        Stage primaryStage = new Stage();

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

        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public static void close()
    {

    }
}
