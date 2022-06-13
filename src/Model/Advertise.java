package Model;


import javafx.scene.image.Image;

public class Advertise
{
    private Image image;

    private String title;

    private String owner;

    private String price;

    public Advertise(Image image, String title, String owner, String price)
    {
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.price = price;
    }

    public Image getImage()
    {
        return image;
    }

    public String getTitle()
    {
        return title;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getPrice()
    {
        return price;
    }
}
