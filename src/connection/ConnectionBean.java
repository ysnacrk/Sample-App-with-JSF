package connection;

//for connection to database

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBean implements Serializable {
    Connection connection = null;

    public Connection getConnection() {

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/staj_takip" , "postgres","test0000");

        }
        catch (ClassNotFoundException | SQLException exception){
                System.out.println("hata" + exception);
        }

        return connection;
    }



}
