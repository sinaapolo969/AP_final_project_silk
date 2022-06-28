package Controller;

import Model.PageControl;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private JFXComboBox<States> state;

    @FXML
    private Label msg;

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
    void back(MouseEvent event) throws IOException
    {
        PageControl.open("Home");
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
            msg.setText("false password confirmation");
        }
        else if (!email.getText().equals(confirmEmail.getText()))
        {
            msg.setText("false email confirmation");
        }
        else
        {
            if (nullChecker())
            {
                User user = new User(username.getText(), pass.getText(), name.getText(), lastName.getText(),
                        number.getText(), email.getText(), state.getValue().toString(), profile);

                Request.signUp(user);
                LoggedHomeController.currentUser = user;
                try
                {
                    PageControl.open("LoggedHome");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

    private Boolean nullChecker()
    {
        if (name.getText().equals(""))
        {
            msg.setText("name not entered!");
            return false;
        }
        if (lastName.getText().equals(""))
        {
            msg.setText("lastname not entered!");
            return false;
        }
        if (username.getText().equals(""))
        {
            msg.setText("username not entered!");
            return false;
        }
        if (pass.getText().equals(""))
        {
            msg.setText("password not entered!");
            return false;
        }
        if (confirmPass.getText().equals(""))
        {
            msg.setText("confirmPassword not entered!");
            return false;
        }
        if (email.getText().equals(""))
        {
            msg.setText("email not entered!");
            return false;
        }
        if (confirmEmail.getText().equals(""))
        {
            msg.setText("confirmEmail not entered!");
            return false;
        }
        if (number.getText().equals(""))
        {
            msg.setText("number not entered!");
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
        File file = new File("D:/final project/divar/src/View/Images/user.png");
        this.profile = file;
        Image image = new Image("/View/Images/user.png");
        photo.setFill(new ImagePattern(image));

        state.getItems().addAll(States.values());
    }
}

