package Controller;

import Model.PageControl;
import Model.Person.User.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static Controller.HomeController.loadedCount;
import static Controller.HomeController.posts;

public class LoggedHomeController implements Initializable
{
    private int column = 0, row = 1, num = 1;
    public static User currentUser;
    @FXML
    private StackPane stack;

    @FXML
    private AnchorPane home;

    @FXML
    private GridPane grid;

    @FXML
    private HBox pages;

    @FXML
    private JFXButton loadM;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private Circle profile;

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
    void search(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/filters.fxml")));
        JFXDialog dialog = new JFXDialog(stack, (Region) root, JFXDialog.DialogTransition.TOP);
        dialog.show();
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

        PageControl.loading15(num, row, column, grid, posts, "AdvertisePre");
        if (loadedCount == posts.size())
        {
            loadM.setDisable(true);
            loadM.setVisible(false);
        }
    }

    public void loadMore(ActionEvent actionEvent)
    {
        num ++;
        row += 5;
        PageControl.loading15(num, row, column, grid, posts, "AdvertisePre");
        if (loadedCount == posts.size())
        {
            loadM.setDisable(true);
            loadM.setVisible(false);
        }
    }

}
