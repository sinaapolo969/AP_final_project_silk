package Controller;

import Model.Advertise;
import Model.Common;
import Model.PageControl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable
{
    public static ArrayList<Advertise> advertises = new ArrayList<>();

    @FXML
    private GridPane grid;

    @FXML
    private HBox pages;

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
        Common.initialDrawer("HomeMenu", leftDrawer);

        //loading advertises
        try
        {
            //need to be deleted (temporary)-------------
            advertises.clear();
            Image img = new Image("/View/Images/rr.jpg");
            for (int i = 0; i < 40; i ++)
            {
                Advertise ad = new Advertise(img, "Portrait", "anonymous", "20");
                advertises.add(ad);
            }
            for (int i = 0; i < 20; i ++)
            {
                Advertise ad = new Advertise(img, "Portrait", "anonymous", "2");
                advertises.add(ad);
            }
            //-------------------------------------------

            pagesButtons();
            loading32(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //creating needed buttons to show 30 advertises on each page
    private void pagesButtons() throws IOException
    {
        for (int i = 0; i < Math.ceil(advertises.size() / 32.0); i ++)
        {
            JFXButton page = PageControl.loader("PageNumber").load();
            page.setText(Integer.toString(i + 1));
            pages.getChildren().add(page);
        }
    }

    public void clearingGridPane()
    {
        while(grid.getRowConstraints().size() > 1)
        {
            grid.getRowConstraints().remove(0);
        }

        while(grid.getColumnConstraints().size() > 0)
        {
            grid.getColumnConstraints().remove(0);
        }
    }

    public void loading32(int num)
    {
        //clearingGridPane();
        int column = 0, row = 1;

        try
        {
            for (int i = 32 * (num - 1); i < 32 * num; i ++)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/AdvertisePre.fxml"));
                VBox vbox = loader.load();

                AdvertisePreController adCon = loader.getController();
                adCon.setData(HomeController.advertises.get(i));

                if (column == 4)
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
        Common.openOrCloseDrawer(leftDrawer);
    }

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
