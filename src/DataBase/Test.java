package DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test
{
    public static void main(String[] args) {
        DbHandler dbHandler = new DbHandler();
        try
        {
            Connection connection = dbHandler.getDbConnection();
            String query = "select firstName from Users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("firstName " +  resultSet.getString("firstName"));
            }

        }
        catch (IOException | SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
