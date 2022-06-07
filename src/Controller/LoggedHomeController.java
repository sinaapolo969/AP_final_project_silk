package Controller;

import Model.Common;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Common.initialDrawer("UserMenu", userDrawer);
        Common.initialDrawer("HomeMenu", leftDrawer);
    }
}
