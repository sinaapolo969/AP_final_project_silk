package Controller;

import Model.Post.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;

public class AdvertisePreController
{
    private Post currentPost;

    @FXML
    public ImageView image;

    @FXML
    public Label title;

    @FXML
    public Label owner;

    @FXML
    public Label price;

    @FXML
    private VBox advertise;

    @FXML
    void bookmark(ActionEvent event)
    {

    }

    @FXML
    void detail(ActionEvent event)
    {

    }

    @FXML
    void ad(MouseEvent event)
    {
//        ScaleTransition t = new ScaleTransition(Duration.seconds(5), advertise);
//        t.setToX(1.2);
//        t.setToY(1.2);
//        t.play();
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
