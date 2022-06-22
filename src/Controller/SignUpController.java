package Controller;

import Model.PageControl;
import Model.Person.User.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class SignUpController implements Initializable
{
    private Image profile;

    @FXML
    private Circle photo;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField confirmEmail;

    @FXML
    private JFXPasswordField confirmPass;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField number;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private JFXComboBox<String> city;

    @FXML
    private void linkedIn(MouseEvent mouseEvent) throws IOException
    {
        PageControl.linkedIn();
    }

    @FXML
    private void instagram(MouseEvent mouseEvent) throws IOException
    {
        PageControl.instagram();
    }

    @FXML
    private void twitter(MouseEvent mouseEvent) throws IOException
    {
        PageControl.twitter();
    }

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        PageControl.openOrCloseDrawer(leftDrawer);
    }


    @FXML
    void fileChoose(ActionEvent event)
    {
        Image profile = PageControl.fileChoose();

        if (profile != null)
        {
            photo.setFill(new ImagePattern(profile));
        }
    }

    @FXML
    private void logIn(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("Login");
    }


    @FXML
    private void signUp(ActionEvent actionEvent)
    {
        if (!pass.equals(confirmPass))
        {
            out.println("confirmation of password is false");
        }
        else if (!email.equals(confirmEmail))
        {
            out.println("confirmation of email is false");
        }
        else
        {
            User user = new User(username.getText(), pass.getText(), name.getText(), lastName.getText(),
                    email.getText(),number.getText(), city.getValue(), profile);
            user.signUp(user);
        }
    }

    @FXML
    void home(MouseEvent event) throws IOException
    {
        PageControl.open("Home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PageControl.initialDrawer("HomeMenu", leftDrawer);

        Image profile = new Image(getClass().getResource("/View/Images/user.png").toExternalForm());
        this.profile = profile;
        photo.setFill(new ImagePattern(profile));

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Chicago");
        cities.add("NewYork");
        cities.add("Washington");
        cities.add("Seattle");
        cities.add("San Francisco");
        cities.add("Miami");
        cities.add("Orlando");

        city.getItems().addAll(cities);
    }
}
