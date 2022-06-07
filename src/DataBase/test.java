package DataBase;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class test
{
    static String json = "{\n" +
            "  \"firstName\": \"mmadreza\",\n" +
            "  \"lastName\": \"tayebi\",\n" +
            "  \"userName\": \"sinaApolo\",\n" +
            "  \"password\": \"sina1381\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    static String json2 = "{\n" +
            "  \"firstName\": \"mmadreza\",\n" +
            "  \"lastName\": \"tayebi\",\n" +
            "  \"phoneNumber\": \"11111\",\n" +
            "  \"emailAddress\": \"sdadfdfsd\",\n" +
            "  \"location\": \"abodan\"\n" +
            "}";
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException
    {
        DbHandler dbHandler = new DbHandler();
        //dbHandler.insertUserData(json);
        //dbHandler.updateUserData(json2);
        //dbHandler.deleteUserData("sinaApolo");
        String json = dbHandler.getUserData("sinaapolo");
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.getString("userName"));

    }
}
