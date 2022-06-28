package Controller;

import Model.PageControl;
import Model.Person.User.Request;
import Model.States;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FiltersController implements Initializable
{
    @FXML
    private JFXComboBox<States> state;

    @FXML
    private ToggleGroup category;


    @FXML
    private JFXTextField start;

    @FXML
    private JFXTextField end;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        state.getItems().addAll(States.values());
    }

    @FXML
    void done(ActionEvent event) throws IOException
    {
        String selectedCategory;
        if (category.getSelectedToggle() != null)
        {
            selectedCategory = category.getSelectedToggle().toString();
        }
        else
        {
            selectedCategory = "";
        }

        States usState = state.getValue();
        int startPrice;
        int endPrice;
        if (!start.getText().equals("") && !end.getText().equals(""))
        {
            startPrice = Integer.parseInt(start.getText());
            endPrice = Integer.parseInt(end.getText());
        }
        else
        {
            startPrice = 0;
            endPrice = 0;
        }


        boolean byPrice = !(startPrice == 0) & !(endPrice == 0);

        if (!byPrice)
        {
            if(usState == null)
            {
                if (!selectedCategory.equals(""))
                {
                    HomeController.posts = Request.getPostByCategory(selectedCategory);
                }
            }
            else
            {
                if (!selectedCategory.equals(""))
                {
                    HomeController.posts = Request.getPostsByLocationAndCategory(selectedCategory, usState.toString());
                }
                else
                {
                    HomeController.posts = Request.getPostByLocation(usState.toString());
                }
            }
        }
        else
        {
            if(usState == null)
            {
                if (!selectedCategory.equals(""))
                {
                    //Request.getPost(selectedCategory);
                }
                else
                {
                    HomeController.posts = Request.getFilteredPricedPosts(startPrice, endPrice);
                }
            }
            else
            {
                if (!selectedCategory.equals(""))
                {
                    //HomeController.posts = Request.getPostsByLocationAndCategoryAndLimitedPrice(startPrice, endPrice, selectedCategory, usState.toString());
                }
                else
                {
                    //HomeController.posts = Request.getFilteredPricedAndLocationPosts(startPrice, endPrice, usState.toString());
                }
            }

        }

        if(LoggedHomeController.currentUser == null)
        {
            PageControl.open("Home");
        }
        else
        {
            PageControl.open("LoggedHome");
        }
    }
}
