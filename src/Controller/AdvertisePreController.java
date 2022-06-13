package Controller;

import Model.Advertise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    void bookmark(ActionEvent event)
    {

    }

    @FXML
    void detail(ActionEvent event)
    {

    }

    public void setData(Advertise ad)
    {
        image.setImage(ad.getImage());
        title.setText(ad.getTitle());
        owner.setText(ad.getOwner());
        price.setText(ad.getPrice());
    }
}
