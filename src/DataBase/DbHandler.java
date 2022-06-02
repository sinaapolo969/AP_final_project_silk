package DataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbHandler
{
    Connection connection;

    public Connection getDbConnection() throws IOException, SQLException, ClassNotFoundException
    {
        Properties properties = getProperties();
        String connectionString = properties.getProperty("db.url") +
                properties.getProperty("db.host") + ":" + properties.getProperty("db.port") + "/" +
                properties.getProperty("db.name");
        Class.forName(properties.getProperty("db.driver"));
        connection = DriverManager.getConnection(connectionString,
                properties.getProperty("db.username"), properties.getProperty("db.password"));
        return connection;
    }
    public Properties getProperties() throws IOException
    {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(
                Paths.get("src/DataBase/db.properties").toFile());
        properties.load(inputStream);
        return properties;
    }
}
