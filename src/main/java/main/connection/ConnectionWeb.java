package main.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionWeb {

    private static final String URL="jdbc:postgresql://localhost:5433/empl_java";
    private static final String LOGIN="postgres";
    private static final String PASSWORD="78789878";
    private static final String DRIVER="org.postgresql.Driver";

    private static ConnectionWeb instance;
    private Connection connection;


    private ConnectionWeb() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionWeb getInstance() {
        if (instance == null) {
            instance = new ConnectionWeb();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new ConnectionWeb();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }


}