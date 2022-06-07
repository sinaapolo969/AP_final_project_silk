package Controller;

import Model.Common;
import Model.PageControl;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController implements Initializable
{
    @FXML
    private ImageView chosen;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField confirmPass;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField number;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXDrawer leftDrawer;

    @FXML
    private void linkedIn(MouseEvent mouseEvent) throws IOException
    {
        Common.linkedIn();
    }

    @FXML
    private void instagram(MouseEvent mouseEvent) throws IOException
    {
        Common.instagram();
    }

    @FXML
    private void twitter(MouseEvent mouseEvent) throws IOException
    {
        Common.twitter();
    }

    @FXML
    private void userMenu(MouseEvent mouseEvent)
    {

    }

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        Common.openOrCloseDrawer(leftDrawer);
    }


    @FXML
    void fileChoose(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            chosen.setImage(image);
        }
        catch (IOException ex)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void logIn(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("Login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Common.initialDrawer("HomeMenu", leftDrawer);

//        Image profile = new Image(getClass().getResource("/View/user.png").toExternalForm());
//        common.setFill(new ImagePattern(profile));
    }
}
