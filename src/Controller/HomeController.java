package Controller;

import Model.PageControl;
import Model.Post.Post;
import Model.Post.PostRequests;
import Network.Client;
import com.jfoenix.controls.JFXDrawer;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable
{
    public static ArrayList<Post> posts = new ArrayList<>();

    private int column = 0, row = 1, num = 1;

    public static String location;

    @FXML
    private ImageView base;
    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane home;


    @FXML
    private JFXDrawer leftDrawer;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //calling splash loader method without falling into a loop
        if (!StartController.isSplashed)
        {
            try
            {
                loadSplash();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //left drawer initializing
        PageControl.initialDrawer("HomeMenu", leftDrawer);

        //loading advertises
        try
        {
            Client client = new Client();
            Socket socket = client.setUp();
            PostRequests request = new PostRequests(socket);
            posts = request.getPostByLocation("Chicago");

            loading15(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        timelineImages();
    }

    private void timelineImages()
    {
        Image one = new Image("View/Images/lrHome/15.jpg");
        Image two = new Image("View/Images/lrHome/7.jpg");
        Image three = new Image("View/Images/lrHome/12.jpg");
        Image four = new Image("View/Images/lrHome/2.jpg");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(base.imageProperty(), one)),
                new KeyFrame(Duration.seconds(5), new KeyValue(base.imageProperty(), two)),
                new KeyFrame(Duration.seconds(10), new KeyValue(base.imageProperty(), three)),
                new KeyFrame(Duration.seconds(15), new KeyValue(base.imageProperty(), four)),
                new KeyFrame(Duration.seconds(20), new KeyValue(base.imageProperty(), null))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void loadMore(ActionEvent event)
    {
        num ++;
        loading15(num);
    }

    public void loading15(int num)
    {
        try
        {
            for (int i = 15 * (num - 1); i < posts.size(); i ++)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/AdvertisePre.fxml"));
                VBox vbox = loader.load();

                AdvertisePreController adCon = loader.getController();
                adCon.setData(posts.get(i));

                if (column == 3)
                {
                    column = 0;
                    row ++;
                }

                this.grid.add(vbox, column ++, row); //child, column, row
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void menu(MouseEvent mouseEvent)
    {
        PageControl.openOrCloseDrawer(leftDrawer);
    }

    @FXML
    private void linkedIn(MouseEvent mouseEvent) throws IOException
    {
        PageControl.linkedIn();
    }

    @FXML
    private void instagram(MouseEvent mouseEvent) throws IOException
    {
        PageControl.instagram();
    }

    @FXML
    private void twitter(MouseEvent mouseEvent) throws IOException
    {
        PageControl.twitter();
    }

    @FXML
    private void signUp(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("SignUp");
    }

    @FXML
    private void logIn(ActionEvent actionEvent) throws IOException
    {
        PageControl.open("Login");
    }

    //creating a fade in on splash when running the application
    public void loadSplash() throws IOException
    {
        StartController.isSplashed = true;
        try
        {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
            home.getChildren().setAll(anchor);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), anchor);

            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            fadeIn.play();
            fadeInFinish(fadeIn);
        }
        catch (Exception ex)
        {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //finishing fade in process
    private void fadeInFinish(FadeTransition fadeIn)
    {
        fadeIn.setOnFinished((e) ->
        {
            try
            {
                AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/View/Home.fxml")));
                home.getChildren().setAll(parentContent);
            }
            catch (IOException ex)
            {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
