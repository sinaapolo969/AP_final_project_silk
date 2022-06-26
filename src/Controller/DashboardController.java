package Controller;

import Model.Post.PostRequests;
import Network.Client;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    @FXML
    private Label nameTop;

    @FXML
    private Circle photo;

    @FXML
    private Label userTop;

    @FXML
    private Label numberTop;

    @FXML
    private Label addPhoto;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXComboBox<?> category;

    @FXML
    private JFXButton createAd;

    @FXML
    private JFXPasswordField oldPass;

    @FXML
    private JFXPasswordField NewPass;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField number;

    @FXML
    private JFXComboBox<?> chooseCity;

    @FXML
    void addPhoto(MouseEvent event)
    {

    }

    @FXML
    void createAd(ActionEvent event)
    {
        Client client = new Client();
        Socket socket = client.setUp();
        PostRequests request = new PostRequests(socket);
//        request.;
    }

    @FXML
    void passReset(ActionEvent event)
    {

    }

    @FXML
    void editInfo(ActionEvent event)
    {

    }

    @FXML
    void profileAdder(MouseEvent event)
    {

    }

    @FXML
    void instagram(MouseEvent event)
    {

    }

    @FXML
    void linkedIn(MouseEvent event)
    {

    }

    @FXML
    void twitter(MouseEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
