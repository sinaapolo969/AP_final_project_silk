package Model;

import Controller.AdvertisePreController;
import Controller.EditPostController;
import Controller.HomeController;
import Model.Post.Post;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

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
        catch(Exception ignored)
        {

        }
    }

    public static FXMLLoader loader(String name)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HomeController.class.getResource("/View/" + name + ".fxml"));
        return loader;
    }

    public static File fileChoose()
    {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        return file;
    }

    public static void instagram() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://www.instagram.com/divar.official/"));
    }

    public static void linkedIn() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://www.linkedin.com/company/divarofficial/"));
    }

    public static void twitter() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://twitter.com/divar_official/"));
    }

    public static void initialDrawer(String name, JFXDrawer drawer)
    {
        try
        {
            AnchorPane anc = FXMLLoader.load(Objects.requireNonNull(PageControl.class.getResource
                    ("/View/" + name + ".fxml")));
            drawer.setSidePane(anc);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void openOrCloseDrawer(JFXDrawer drawer)
    {
        if (drawer.isOpened())
        {
            drawer.close();
        }
        else
        {
            drawer.open();
        }
    }

    public static void loading15(int num, int row, int column, GridPane grid,
                                 ArrayList<Post> posts, String name)
    {
        int min = Math.min(15, posts.size() - 15 * (num - 1));
        HomeController.loadedCount += min;

        try
        {
            for (int i = 15 * (num - 1); i < min + (15 * (num - 1)); i ++)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(PageControl.class.getResource("/View/" + name + ".fxml"));
                VBox vbox = loader.load();

                if (name.equals("AdvertisePre"))
                {
                    AdvertisePreController adCon = loader.getController();
                    adCon.currentPost = posts.get(i);
                    adCon.setData(posts.get(i));
                }
                else if (name.equals("editPost"))
                {
                    EditPostController editPost = loader.getController();
                    editPost.currentPost = posts.get(i);
                    editPost.setData(posts.get(i));
                }

                if (column == 3)
                {
                    column = 0;
                    row ++;
                }

                grid.add(vbox, column ++, row); //child, column, row
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
