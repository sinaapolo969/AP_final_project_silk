package Controller;

import Model.PageControl;
import Model.Person.User.Request;
import Model.Post.Post;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PostVisitController implements Initializable
{
    Boolean bookmarked = false;
    static Post currentPost;
    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label owner;

    @FXML
    private JFXButton bk;

    @FXML
    private Label number;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField price;

    @FXML
    private Circle seller;

    @FXML
    private Label name;

    @FXML
    private Tab chat;

    @FXML
    private FontAwesomeIconView icon1;


    @FXML
    void copy(MouseEvent event)
    {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(number.getText());
        clipboard.setContent(content);
    }

    @FXML
    void bookmark(MouseEvent event) throws IOException
    {
        if (LoggedHomeController.currentUser == null)
        {
            PageControl.open("SignUp");
        }
        else
        {
            Request.bookmarking(currentPost.getPostId(), LoggedHomeController.currentUser.getUserName());
            bk.setStyle("-fx-background-color: #914c4c");
        }
    }

    @FXML
    void back(MouseEvent event) throws IOException
    {
        if (LoggedHomeController.currentUser == null)
        {
            PageControl.open("Home");
        }
        else
        {
            PageControl.open("LoggedHome");
        }
    }

    @FXML
    void home(MouseEvent event) throws IOException
    {
        if (LoggedHomeController.currentUser == null)
        {
            PageControl.open("Home");
        }
        else
        {
            PageControl.open("LoggedHome");
        }
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
    void menu(MouseEvent event)
    {

    }

    @FXML
    void twitter(MouseEvent event) throws IOException
    {
        PageControl.twitter();
    }

    @FXML
    void userMenu(MouseEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        price.setText(String.valueOf(currentPost.getPrice()));
        category.setText(currentPost.getCategory());
        description.setText(currentPost.getDescription());
        File file = currentPost.getPhoto();
        image.setImage(new Image(file.toURI().toString()));
        number.setText(currentPost.getPhoneNumber());
        owner.setText(currentPost.getOwner());
        title.setText(currentPost.getTitle());
        file = currentPost.getPhoto();
        Image image = new Image(file.toURI().toString());
        seller.setFill(new ImagePattern(image));
        name.setText(currentPost.getOwner());

        if (LoggedHomeController.currentUser == null)
        {
            chat.setDisable(true);
            icon1.setDisable(true);
            icon1.setVisible(false);
            bk.setDisable(true);
            bk.setVisible(false);
        }
    }
}
