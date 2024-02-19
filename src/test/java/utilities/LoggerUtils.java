package utilities;


import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);
    public static void info(String log){
        logger.info(log);
        Allure.step(log);
    }

}
