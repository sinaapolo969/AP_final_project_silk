package Controller;

import Model.Main;
import Model.PageControl;
import Model.Person.User.Request;
import Model.Post.Post;
import Model.Post.PostRequests;
import Network.Client;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    private int columnB = 0, rowB = 1, numB = 1;
    private int columnE = 0, rowE = 1, numE = 1;

    public static ArrayList<Post> bookmarks = new ArrayList<>();
    public static ArrayList<Post> mine = new ArrayList<>();

    File advertise = new File("View/Images/ad2.png");

    @FXML
    private GridPane gridBM;

    @FXML
    private GridPane gridEdit;

    @FXML
    private Circle photo;

    @FXML
    private Label addPhoto;

    @FXML
    private Label numberTop;

    @FXML
    private Label nameTop;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXButton createAd;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField lastname;

    @FXML
    private Label username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField number;

    @FXML
    private JFXComboBox<String> chooseCity;

    @FXML
    private ImageView advertisement;

    @FXML
    void addPhoto(MouseEvent event)
    {
        File file = PageControl.fileChoose();
        Image image = new Image(file.toURI().toString());
        advertisement.setImage(image);

        advertise = file;
    }

    @FXML
    void createAd(ActionEvent event)
    {
//        Post newPost = new Post(title.getText(), ,category.getValue(), description.getText(),
//                price.getText(), "true", LoggedHomeController.currentUser.getUserName(), advertise,
//                LoggedHomeController.currentUser.getPhoneNumber(), LoggedHomeController.currentUser.getLocation())
//        Client client = new Client();
//        Socket socket = client.setUp();
//        PostRequests request = new PostRequests(socket);
//        request.makingPost(newPost);
    }

    @FXML
    void editInfo(ActionEvent event)
    {
        LoggedHomeController.currentUser.setFirstName(name.getText());
        LoggedHomeController.currentUser.setLastName(lastname.getText());
        LoggedHomeController.currentUser.setLocation(chooseCity.getValue());
        LoggedHomeController.currentUser.setPhoneNumber(number.getText());
        LoggedHomeController.currentUser.setEmail(email.getText());
        LoggedHomeController.currentUser.setPassword(pass.getText());

        Client client = new Client();
        Socket socket = client.setUp();
        Request request = new Request(socket);

        request.editInfo(LoggedHomeController.currentUser);
    }

    @FXML
    void profileAdder(MouseEvent event)
    {
        File file = PageControl.fileChoose();
        Image image = new Image(file.toURI().toString());
        LoggedHomeController.currentUser.setProfile(file);

        Client client = new Client();
        Socket socket = client.setUp();
        Request request = new Request(socket);
        request.editInfo(LoggedHomeController.currentUser);
    }

    @FXML
    void back(MouseEvent event) throws IOException
    {
        PageControl.open("LoggedHomeMenu");
    }

    @FXML
    void instagram(MouseEvent event) throws IOException
    {
        PageControl.instagram();
    }

    @FXML
    void linkedIn(MouseEvent event) throws IOException
    {
        PageControl.linkedIn();
    }

    @FXML
    void twitter(MouseEvent event) throws IOException
    {
        PageControl.twitter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //bookmarks tab
        PostRequests postRequests = new PostRequests(new Client().setUp());
        bookmarks = postRequests.gettingBookmarks(LoggedHomeController.currentUser.getUserName());
        PageControl.loading15(numB, rowB, columnB, gridBM, bookmarks);

        //my advertisements tab
        bookmarks = postRequests.getPostByOwner(LoggedHomeController.currentUser.getUserName());
        PageControl.loading15(numE, rowE, columnE, gridEdit, mine);

        //user profile in the top of the page and edit info page
        Image image = new Image(LoggedHomeController.currentUser.getProfile().toURI().toString());
        photo.setFill(new ImagePattern(image));

        name.setText(LoggedHomeController.currentUser.getFirstName());

        chooseCity.setValue(LoggedHomeController.currentUser.getLocation());

        lastname.setText(LoggedHomeController.currentUser.getLastName());

        username.setText(LoggedHomeController.currentUser.getUserName());

        number.setText(LoggedHomeController.currentUser.getPhoneNumber());

        email.setText(LoggedHomeController.currentUser.getEmail());

        pass.setText(LoggedHomeController.currentUser.getPassword());

        nameTop.setText(LoggedHomeController.currentUser.getFirstName() + " " +
                LoggedHomeController.currentUser.getLastName());

        numberTop.setText(LoggedHomeController.currentUser.getPhoneNumber());

        chooseCity.getItems().addAll(Main.cities);

    }
}
