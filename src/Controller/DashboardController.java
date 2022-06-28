package Controller;

import Model.PageControl;
import Model.Person.User.Request;
import Model.Post.Categories;
import Model.Post.Post;
import Model.States;
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

    public static ArrayList<Post> bookmarks = new ArrayList<>();
    public static ArrayList<Post> mine = new ArrayList<>();
    File advertise = new File("D:/final project/divar/src/View/Images/ad2.png");
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
    private JFXComboBox<Categories> category;

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
    private JFXComboBox<States> state;

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
        Post newPost = new Post(title.getText() ,category.getValue().toString(), description.getText(),
                Double.parseDouble(price.getText()), "0",
                LoggedHomeController.currentUser.getUserName(), advertise,
                LoggedHomeController.currentUser.getPhoneNumber(),
                LoggedHomeController.currentUser.getLocation());
        Request.makingPost(newPost);
    }

    @FXML
    void editInfo(ActionEvent event)
    {
        LoggedHomeController.currentUser.setFirstName(name.getText());
        LoggedHomeController.currentUser.setLastName(lastname.getText());
        LoggedHomeController.currentUser.setLocation(state.getValue().toString());
        LoggedHomeController.currentUser.setPhoneNumber(number.getText());
        LoggedHomeController.currentUser.setEmail(email.getText());
        LoggedHomeController.currentUser.setPassword(pass.getText());

        Request.editInfo(LoggedHomeController.currentUser);
        userDataSet();
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


    @FXML
    void loadMoreB(ActionEvent event)
    {
        numB ++;
        rowB += 5;
            PageControl.loading15(numB, rowB, columnB, gridBM,
                    Request.gettingBookmarks(LoggedHomeController.currentUser.getUserName()), "AdvertisePre");
    }

    @FXML
    void loadMoreE(ActionEvent event)
    {
        numE ++;
        rowE += 5;
        PageControl.loading15(numE, rowE, columnE, gridEdit,
                Request.gettingBookmarks(LoggedHomeController.currentUser.getUserName()), "editPost");
    }

    private void userDataSet()
    {
        name.setText(LoggedHomeController.currentUser.getFirstName());
        lastname.setText(LoggedHomeController.currentUser.getLastName());
        username.setText(LoggedHomeController.currentUser.getUserName());
        number.setText(LoggedHomeController.currentUser.getPhoneNumber());
        email.setText(LoggedHomeController.currentUser.getEmail());
        pass.setText(LoggedHomeController.currentUser.getPassword());
        nameTop.setText(LoggedHomeController.currentUser.getFirstName() + " " +
                LoggedHomeController.currentUser.getLastName());
        numberTop.setText(LoggedHomeController.currentUser.getPhoneNumber());
        state.getItems().addAll(States.values());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //bookmarks tab
        bookmarks = Request.gettingBookmarks(LoggedHomeController.currentUser.getUserName());
        PageControl.loading15(numB, rowB, columnB, gridBM, bookmarks, "AdvertisePre");

        //my advertisements tab
        mine = Request.getPostByOwner(LoggedHomeController.currentUser.getUserName());
        PageControl.loading15(numE, rowE, columnE, gridEdit, mine, "editPost");

        //user profile photo in the top of the page
        Image image = new Image(LoggedHomeController.currentUser.getProfile().toURI().toString());
        photo.setFill(new ImagePattern(image));

        //category comboBox
        state.getItems().addAll(States.values());

        category.getItems().addAll(Categories.values());

        //user data in the top of the page and edit info tab
        userDataSet();
    }
}
