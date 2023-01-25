package App;

import DataBase.PostTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class temp
{
    public static void main(String[] args) throws SQLException {
        PostTable postTable = new PostTable();
        ArrayList<String> posts = postTable.getPostDataByOwner("sina");
        System.out.println(posts);
    }
}
