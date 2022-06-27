package Controller;

import Model.PageControl;
import Model.Person.User.User;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedHomeController implements Initializable
{
    public static User currentUser;
    @FXML
    private AnchorPane home;

    @FXML
    private JFXDrawer filter;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private Circle profile;

    @FXML
    private JFXTextField search;

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        PageControl.openOrCloseDrawer(leftDrawer);
    }

    @FXML
    private void userMenu(MouseEvent mouseEvent)
    {

    }

    @FXML
    void search(ActionEvent event)
    {
        PageControl.openOrCloseDrawer(filter);
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
        PageControl.initialDrawer("LoggedHomeMenu", leftDrawer);
        String url = currentUser.getProfile().toURI().toString().replaceAll("file:/", "file:///");
        Image image  = new Image(url);
        profile.setFill(new ImagePattern(image));
    }

    public void loadMore(ActionEvent actionEvent)
    {

    }

}
