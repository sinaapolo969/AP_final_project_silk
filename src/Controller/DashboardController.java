package Controller;

import Model.PageControl;
import Model.Post.Post;
import Model.Post.PostRequests;
import Network.Client;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    private int column = 0, row = 1, num = 1;

    public static ArrayList<Post> bookmarks = new ArrayList<>();
    public static ArrayList<Post> mine = new ArrayList<>();

    @FXML
    private GridPane gridBM;

    @FXML
    private Label nameTop;

    @FXML
    private Circle photo;

    @FXML
    private Label userTop;

    @FXML
    private Label numberTop;

    @FXML
    private Label addPhoto;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXComboBox<?> category;

    @FXML
    private JFXButton createAd;

    @FXML
    private JFXPasswordField oldPass;

    @FXML
    private JFXPasswordField NewPass;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField number;

    @FXML
    private JFXComboBox<?> chooseCity;

    @FXML
    void addPhoto(MouseEvent event)
    {

    }

    @FXML
    void createAd(ActionEvent event)
    {
        Client client = new Client();
        Socket socket = client.setUp();
        PostRequests request = new PostRequests(socket);
//        request.;
    }

    @FXML
    void passReset(ActionEvent event)
    {

    }

    @FXML
    void editInfo(ActionEvent event)
    {

    }

    @FXML
    void profileAdder(MouseEvent event)
    {

    }

    @FXML
    void instagram(MouseEvent event)
    {

    }

    @FXML
    void linkedIn(MouseEvent event)
    {

    }

    @FXML
    void twitter(MouseEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PostRequests postRequests = new PostRequests(new Client().setUp());
        //bookmarks = postRequests.gettingBookmarks(LoggedHomeController.currentUser.getUserName());
        PageControl.loading15(num, row, column, gridBM, bookmarks);
    }
}
