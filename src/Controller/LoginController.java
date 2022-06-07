package Controller;

import Model.Common;
import Model.PageControl;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private Circle common;

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
    private void userMenu(MouseEvent mouseEvent)
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

        Image profile = new Image(getClass().getResource("/View/user.png").toExternalForm());
        common.setFill(new ImagePattern(profile));
    }

    public void createNewAcc(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("SignUp");
    }
}
