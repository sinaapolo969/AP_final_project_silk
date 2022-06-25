package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PageNumberController
{

    @FXML
    private JFXButton page;

    @FXML
    void toPage(ActionEvent event)
    {
        int num = Integer.parseInt(page.getText());
        System.out.println(num);
        HomeController h = new HomeController();
        h.loading15(num);
    }
}
