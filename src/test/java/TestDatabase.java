import main.connection.ConnectionFactory;
import main.exceptions.DatabaseConnectionException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TestDatabase {

    @Test
    public void databaseConnectionTest() throws DatabaseConnectionException {
        Connection connection = ConnectionFactory.getConnectionBuilder().getConnection();

        Assert.assertNotNull(connection);
    }

}
