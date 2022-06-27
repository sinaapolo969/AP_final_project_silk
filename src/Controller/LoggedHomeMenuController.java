package Controller;

import Model.PageControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedHomeMenuController implements Initializable
{
    @FXML
    private AnchorPane info;

    @FXML
    private Label name;

    @FXML
    private Label username;

    @FXML
    private Label number;

    @FXML
    private Circle profile;

    @FXML
    private VBox icons;

    @FXML
    private VBox menu;

    @FXML
    void bookmark(ActionEvent event) throws IOException
    {
        PageControl.open("DashBoard");
    }

    @FXML
    void dashboard(ActionEvent event) throws IOException
    {
        PageControl.open("DashBoard");
    }

    @FXML
    void exit(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    void location(ActionEvent event) throws IOException
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image image  = new Image(LoggedHomeController.currentUser.getProfile().toURI().toString());
        profile.setFill(new ImagePattern(image));

        name.setText(LoggedHomeController.currentUser.getFirstName() + " " + LoggedHomeController.currentUser.getLastName());
        username.setText("@" + LoggedHomeController.currentUser.getUserName());
        number.setText((LoggedHomeController.currentUser.getPhoneNumber()));
    }
}
