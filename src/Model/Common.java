package Model;

import Controller.StartController;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URI;

public class Common
{
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
            VBox vb = FXMLLoader.load(Common.class.getResource("/View/" + name + ".fxml"));
            drawer.setSidePane(vb);
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
}
