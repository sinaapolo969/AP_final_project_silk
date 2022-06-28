package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChatController
{
    @FXML
    private Label message;

    public void setData(String text)
    {
        message.setText(text);
    }
}
