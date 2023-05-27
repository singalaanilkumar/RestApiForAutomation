package ApiProjectHighLevel.api;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class Helper {
    String path;

    public String loadProperties(String property) {
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(path);

            // load a properties file

            prop.load(input);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return prop.getProperty(property);
    }

    public Helper set_path(String path2) {
        path = path2;
        return this;
    }

}

