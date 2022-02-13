package connectionPooling.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class delivers status of steps that a Party should complete
 * <p>
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2022.02.07   A.Rayani
 */
public class SqlGenerator {
    public static String generateSlqFromFile(String path) {
        StringBuilder query = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                query.append(line);
                System.out.println(line);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return query.toString();
    }
}
