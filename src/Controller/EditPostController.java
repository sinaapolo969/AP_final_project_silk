package Controller;

import Model.Person.User.Request;
import Model.Post.Post;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPostController implements Initializable
{
    public Post currentPost;
    @FXML
    private VBox advertise;

    @FXML
    private ImageView image;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextArea description;


    @FXML
    void edit(ActionEvent event)
    {
        Request.editPost(currentPost);
    }

    public void setData(Post ad)
    {
        File temp = ad.getPhoto();
        Image profile = new Image(temp.toURI().toString());
        image.setImage(profile);
        price.setText(String.valueOf(ad.getPrice()));
        title.setText(ad.getTitle());
        category.setValue(ad.getCategory());
        description.setText(ad.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
