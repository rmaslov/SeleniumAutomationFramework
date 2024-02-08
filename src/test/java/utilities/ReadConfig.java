package utilities;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    public String getProperty(String key)  {
        Properties p = new Properties();

        try {
            FileReader fr = new FileReader("src/test/resources/configFiles/config.properties");
            p.load(fr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return p.getProperty(key);
    }
}
