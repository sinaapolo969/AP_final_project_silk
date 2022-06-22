package Controller;

import Model.Common;
import Model.PageControl;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    private void signUp(ActionEvent event) throws IOException
    {
        PageControl.open("SignUp");
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        Common.openOrCloseDrawer(leftDrawer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Common.initialDrawer("HomeMenu", leftDrawer);
    }
}
