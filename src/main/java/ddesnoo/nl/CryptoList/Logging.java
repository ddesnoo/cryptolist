package ddesnoo.nl.CryptoList;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {

    /**
     *  This function creates a new Logfile as soon as the programs boots-up
     */
    public static void LogFile() {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("LogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("Log file generated");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * With this function we insert a new log into the Logfile
     */
    public static void Log(String LogText) {
        Logger logger = Logger.getLogger("MyLog");
        logger.info(LogText);
    }

}
