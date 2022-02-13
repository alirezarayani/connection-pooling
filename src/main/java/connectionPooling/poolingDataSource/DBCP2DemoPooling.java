package connectionPooling.poolingDataSource;

import connectionPooling.basicDataSource.DBConnectionDBCP2Basic;
import connectionPooling.util.SqlGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class delivers status of steps that a Party should complete
 * <p>
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2022.02.01   A.Rayani
 */
public class DBCP2DemoPooling {
    private static final String CREATE_TABLE_FILE = "src/main/resources/SampleDatabase-createObjects.sql";
    private static final String SQL_FILE = "src/main/resources/SampleDatabase-loadData.sql";

    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnectionDBCP2Pooling.getConnection();
        connection.setAutoCommit(false);
        String createTable = SqlGenerator.generateSlqFromFile(CREATE_TABLE_FILE);
        Statement preparedStatement = connection.createStatement();
        preparedStatement.executeUpdate(createTable);
        String insertSql = SqlGenerator.generateSlqFromFile(SQL_FILE);
        PreparedStatement preparedStatement1 = connection.prepareStatement(insertSql);
        preparedStatement1.addBatch();
        preparedStatement1.executeBatch();
        connection.commit();
    }
}
