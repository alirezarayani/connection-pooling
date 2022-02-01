package connectionPooling.basicDataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class DBCP2Demo {
    private static BasicDataSource dataSource = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","Passw0rd");
        Statement statement = connection.createStatement();
        ResultSet rs =  statement.executeQuery("Select * from CUSTOMER ");
        System.out.println();
    }
}
