package connectionPooling.poolingDataSource;

import connectionPooling.DBConnection;
import connectionPooling.util.PropertiesFile;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.sql.DataSource;
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
public class DBConnectionDBCP2Pooling {
    private static Properties props = null;
    private static DataSource dataSource = null;

    static {
        synchronized (DBConnection.class) {
            try {
                props = PropertiesFile.readPropertiesFile("src/main/resources/db.properties");
                ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(props.getProperty("DB_URL"), props);
                props.setProperty("user", props.getProperty("DB_USERNAME"));
                props.setProperty("password", props.getProperty("DB_PASSWORD"));
                PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
                GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
                config.setMaxTotal(25);
                config.setMaxIdle(10);
                config.setMinIdle(5);
                ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
                poolableConnectionFactory.setPool(connectionPool);
                dataSource = new PoolingDataSource<>(connectionPool);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
