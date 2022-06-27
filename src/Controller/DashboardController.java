package Controller;

import Model.Main;
import Model.PageControl;
import Model.Person.User.Request;
import Model.Post.Post;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    private int columnB = 0, rowB = 1, numB = 1;
    private int columnE = 0, rowE = 1, numE = 1;

    public static ArrayList<Post> bookmarks = Request.gettingBookmarks(LoggedHomeController.currentUser.getUserName());
    public static ArrayList<Post> mine = Request.getPostByOwner(LoggedHomeController.currentUser.getUserName());
    File advertise = new File("View/Images/ad2.png");
    File profile = LoggedHomeController.currentUser.getProfile();

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
        Post newPost = new Post(title.getText() ,category.getValue(), description.getText(),
                Double.parseDouble(price.getText()), "0", LoggedHomeController.currentUser.getUserName(), advertise,
                LoggedHomeController.currentUser.getPhoneNumber(), LoggedHomeController.currentUser.getLocation());
        Request.makingPost(newPost);
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

        Request.editInfo(LoggedHomeController.currentUser);
    }

    @FXML
    void profileAdder(MouseEvent event)
    {
        profile = PageControl.fileChoose();
        Image image = new Image(profile.toURI().toString());
        LoggedHomeController.currentUser.setProfile(profile);
        photo.setFill(new ImagePattern(image));
    }

    @FXML
    void back(MouseEvent event) throws IOException
    {
        PageControl.open("LoggedHome");
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

        //bookmarks = Request.gettingBookmarks(LoggedHomeController.currentUser.getUserName(), 1);
        PageControl.loading15(numB, rowB, columnB, gridBM, bookmarks, "AdvertisePre");

        //my advertisements tab
//        mine = Request.getPostByOwner(LoggedHomeController.currentUser.getUserName(), 1);
        PageControl.loading15(numE, rowE, columnE, gridEdit, mine, "AdvertisePre");

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
