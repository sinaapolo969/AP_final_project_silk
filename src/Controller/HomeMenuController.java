package Controller;

import Model.PageControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeMenuController implements Initializable
{
    @FXML
    private AnchorPane info;

    @FXML
    private Circle photo;

    @FXML
    private VBox menu;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void signup(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image image = new Image("/View/Images/user.png");
        photo.setFill(new ImagePattern(image));
    }
}
