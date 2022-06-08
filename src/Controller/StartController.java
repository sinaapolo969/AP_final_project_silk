package Controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable
{
    public static boolean isSplashed = false;

    @FXML
    private ImageView img;

    private void rotate()
    {
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), img);
        rotate.setByAngle(360);
        rotate.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        rotate();
    }
}
