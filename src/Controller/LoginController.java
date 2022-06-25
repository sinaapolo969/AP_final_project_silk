package Controller;

import Model.PageControl;
import Model.Person.User.Request;
import Network.Client;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;


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
    private void signUp(ActionEvent event) throws IOException
    {
        PageControl.open("SignUp");
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException
    {
        Client client = new Client();
        Socket socket = client.setUp();
        Request request = new Request(socket);

        if (request.login(username.getText(), password.getText()) == null)
        {

        }
        else
        {
            LoggedHomeController.currentUser = request.login(username.getText(), password.getText());
            LoggedHomeMenuController.currentUser = request.login(username.getText(), password.getText());
            PageControl.open("LoggedHome");
        }
    }

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        PageControl.openOrCloseDrawer(leftDrawer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PageControl.initialDrawer("HomeMenu", leftDrawer);
    }
}
