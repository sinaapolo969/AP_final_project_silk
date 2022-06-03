package Controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
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
        catch (Exception ex)
        {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }
}
