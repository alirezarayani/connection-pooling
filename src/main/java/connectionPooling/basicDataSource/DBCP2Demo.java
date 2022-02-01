package connectionPooling.basicDataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection ("jdbc:h2:tcp://localhost/~/test", "sa","Passw0rd");

        ScriptRunner sr = new ScriptRunner(connection);
        //Creating a reader object
        Reader createObject = new BufferedReader(new FileReader("src/main/resources/SampleDatabase-createObjects.sql"));
        Reader loadDate = new BufferedReader(new FileReader("src/main/resources/SampleDatabase-loadData.sql"));
        //Running the script
        sr.runScript(createObject);
        sr.runScript(loadDate);
    }
}
