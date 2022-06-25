package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DashboardController
{
    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label addPhoto;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXButton createAd;

    @FXML
    void createAd(ActionEvent event) {

    }

    @FXML
    void instagram(MouseEvent event) {

    }

    @FXML
    void linkedIn(MouseEvent event) {

    }

    @FXML
    void twitter(MouseEvent event) {

    }
}
