package Model.Chat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MessageStruct {
    private String text;
    private String senderID;
    private String receiverID;
    private LocalDateTime date;

    public MessageStruct(String text, String senderID, String receiverID, LocalDateTime date)
    {
        this.text = text;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.date = date;
    }

    public MessageStruct(String text, String senderID, String receiverID)
    {
        this.text = text;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.date = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
