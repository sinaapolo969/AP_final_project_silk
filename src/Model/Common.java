package Model;

import java.io.IOException;
import java.net.URI;

public class Common
{
    public static void instagram() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://www.instagram.com/divar.official/"));
    }

    public static void linkedIn() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://www.linkedin.com/company/divarofficial/"));
    }

    public static void twitter() throws IOException
    {
        java.awt.Desktop.getDesktop().browse(URI.create("https://twitter.com/divar_official/"));
    }
}
