package Controller;

import Model.Advertise;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PageNumberController
{

    @FXML
    private JFXButton page;

    @FXML
    void toPage(ActionEvent event)
    {
        HomeController h = new HomeController();
        h.loading32(Integer.parseInt(page.getText()));
    }
}
