package Controller;

import Model.PageControl;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedHomeController implements Initializable
{
    @FXML
    private AnchorPane home;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private Circle common;

    @FXML
    private JFXDrawer userDrawer;

    @FXML
    private void menu(MouseEvent mouseEvent)
    {

    }

    @FXML
    private void userMenu(MouseEvent mouseEvent)
    {

    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PageControl.initialDrawer("UserMenu", userDrawer);
        PageControl.initialDrawer("HomeMenu", leftDrawer);
    }
}
