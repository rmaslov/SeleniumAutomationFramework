package utilities;


import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.SDProductsPage;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(SDProductsPage.class);
    public void info(String log){
        logger.info(log);
        Allure.step(log);
    }

}
