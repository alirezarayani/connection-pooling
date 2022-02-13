package connectionPooling.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class delivers status of steps that a Party should complete
 * <p>
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2022.02.13   A.Rayani
 */
public class PropertiesFile {
    private static Properties props = null;

    public static synchronized Properties readPropertiesFile(String path) {
        try (FileInputStream fis = new FileInputStream(path);) {
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
