package Controller;

import Model.Person.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedHomeMenuController implements Initializable
{
    public static User currentUser;
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
    void advertise(ActionEvent event)
    {

    }

    @FXML
    void bookmark(ActionEvent event) {

    }

    @FXML
    void dashboard(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void location(ActionEvent event) {

    }

    @FXML
    void setting(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image image  = new Image(currentUser.getProfile().toURI().toString());
        profile.setFill(new ImagePattern(image));

        name.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        username.setText("@" + currentUser.getUserName());
        number.setText((currentUser.getPhoneNumber()));
    }
}
