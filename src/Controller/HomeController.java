package Controller;

import Model.Common;
import Model.PageControl;
import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable
{
    @FXML
    private AnchorPane home;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Circle common;

    public void loadSplash() throws IOException
    {
        StartController.isSplashed = true;

        try
        {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
            home.getChildren().setAll(anchor);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), anchor);

            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            fadeIn.play();
            fadeInFinish(fadeIn);
        }
        catch (Exception ex)
        {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fadeInFinish(FadeTransition fadeIn)
    {
        fadeIn.setOnFinished((e) ->
        {
            try
            {
                AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/View/Home.fxml")));
                home.getChildren().setAll(parentContent);
            }
            catch (IOException ex)
            {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (!StartController.isSplashed)
        {
            try
            {
                loadSplash();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            VBox vb = FXMLLoader.load(getClass().getResource("/View/HomeMenu.fxml"));
            drawer.setSidePane(vb);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Image profile = new Image(getClass().getResource("/View/user.png").toExternalForm());
        common.setFill(new ImagePattern(profile));
    }

    @FXML
    private void menu(MouseEvent mouseEvent)
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

    @FXML
    private void userMenu(MouseEvent mouseEvent)
    {

    }

    @FXML
    private void linkedIn(MouseEvent mouseEvent) throws IOException
    {
        Common.linkedIn();
    }

    @FXML
    private void instagram(MouseEvent mouseEvent) throws IOException
    {
        Common.instagram();
    }

    @FXML
    private void twitter(MouseEvent mouseEvent) throws IOException
    {
        Common.twitter();
    }

    @FXML
    private void signUp(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("SignUp");
    }

    @FXML
    private void logIn(ActionEvent actionEvent) throws IOException
    {
        PageControl.close();
        PageControl.open("Login");
    }
}
