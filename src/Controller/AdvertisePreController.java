package Controller;

import Model.Advertise;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AdvertisePreController
{
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

    public void setData(Advertise ad)
    {
        image.setImage(ad.getImage());
        title.setText(ad.getTitle());
        owner.setText(ad.getOwner());
        price.setText(ad.getPrice());
    }
}
