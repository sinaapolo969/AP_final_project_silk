package Controller;

import Model.PageControl;
import Model.Person.User.Request;
import Model.Post.Post;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class AdvertisePreController
{
    public Post currentPost;

    @FXML
    public ImageView image;

    @FXML
    public Label title;

    @FXML
    private JFXButton bk;

    @FXML
    public Label owner;

    @FXML
    public Label price;

    @FXML
    private VBox advertise;

    @FXML
    void bookmark(ActionEvent event)
    {
        Request.bookmarking(currentPost.getPostId(), LoggedHomeController.currentUser.getUserName());
        bk.setStyle("-fx-background-color: #563e3e");
    }

    @FXML
    void detail(ActionEvent event)
    {
        try
        {
            PostVisitController.currentPost = currentPost;
            PageControl.open("PostVisit");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setData(Post ad)
    {
        File temp = ad.getPhoto();
        Image profile = new Image(temp.toURI().toString());
        image.setImage(profile);
        price.setText(String.valueOf(ad.getPrice()));
        title.setText(ad.getTitle());
        owner.setText(ad.getOwner());
    }
}
