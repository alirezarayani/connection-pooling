package connectionPooling.basicDataSource;

import connectionPooling.DBConnection;
import connectionPooling.util.PropertiesFile;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class delivers status of steps that a Party should complete
 * <p>
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2022.02.13   A.Rayani
 */
public class DBConnectionDBCP2Basic {
    private static Properties props = null;
    private static BasicDataSource dataSource;

    static {
        synchronized (DBConnection.class) {
            try {
                props = PropertiesFile.readPropertiesFile("src/main/resources/db.properties");
                dataSource = new BasicDataSource();
                dataSource.setUrl(props.getProperty("DB_URL"));
                dataSource.setUsername(props.getProperty("DB_USERNAME"));
                dataSource.setPassword(props.getProperty("DB_PASSWORD"));
                dataSource.setMinIdle(5);
                dataSource.setMaxIdle(10);
                dataSource.setMaxTotal(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
