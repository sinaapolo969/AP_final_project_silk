package Controller;

import Model.PageControl;
import Model.Post.Post;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PostVisitController implements Initializable
{
    Post currentPost;

    @FXML
    private Circle common;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label owner;

    @FXML
    private Label number;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField price;

    @FXML
    void copy(MouseEvent event)
    {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(number.getText());
        clipboard.setContent(content);
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
        //image.setImage(currentPost.getPhoto());
        number.setText(currentPost.getPhoneNumber());
        owner.setText(currentPost.getOwner());
        title.setText(currentPost.getTitle());
    }
}
