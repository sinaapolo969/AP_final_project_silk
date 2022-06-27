package Controller;

import Model.PageControl;
import Model.Person.Person;
import Model.Person.User.Request;
import Model.Person.User.User;
import Model.States;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Main.cities;
import static java.lang.System.out;

public class SignUpController implements Initializable
{
    private File profile;

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
    private JFXComboBox<String> state;

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
        File profile = PageControl.fileChoose();
        Image image  = new Image(profile.toURI().toString());
        if (image != null)
        {
            this.profile = profile;
            photo.setFill(new ImagePattern(image));
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
        if (!pass.getText().equals(confirmPass.getText()))
        {
            out.println("confirmation of password is false");
        }
        else if (!email.getText().equals(confirmEmail.getText()))
        {
            out.println("confirmation of email is false");
        }
        else
        {
            if (nullChecker())
            {
                User user = new User(username.getText(), pass.getText(), name.getText(), lastName.getText(),
                        number.getText(), email.getText(), String.valueOf(state), profile);

                Request.signUp(user);
            }

        }
    }

    private Boolean nullChecker()
    {
        if (name.getText().equals(""))
        {
            return false;
        }
        if (lastName.getText().equals(""))
        {
            return false;
        }
        if (username.getText().equals(""))
        {
            return false;
        }
        if (pass.getText().equals(""))
        {
            return false;
        }
        if (confirmPass.getText().equals(""))
        {
            return false;
        }
        if (email.getText().equals(""))
        {
            return false;
        }
        if (confirmEmail.getText().equals(""))
        {
            return false;
        }
        if (number.getText().equals(""))
        {
            return false;
        }
        return true;
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

        File file = new File("/View/Images/user.png");
        this.profile = file;
        Image image = new Image("/View/Images/user.png");
        photo.setFill(new ImagePattern(image));

        state.getItems().addAll(States.values().toString());
    }
}

