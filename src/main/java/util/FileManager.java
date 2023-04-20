package util;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static util.Message.getLog;

/**
 * A utility class for managing files and directories.
 */
public class FileManager {
    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    /**
     * Creates a directory with the specified name.
     *
     * @param directoryName the name of the directory to create
     * @return true if the directory was created successfully, false otherwise
     */
    public static boolean createDirectory(String directoryName) {
        File directory = new File(directoryName);

        if (directory.exists()) {
            logger.warning(getLog("dir_exist"));
        } else {
            try {
                File parent = directory.getParentFile();
                if (!parent.exists()) {
                    boolean success = parent.mkdirs();
                    if (!success) {
                        logger.severe(getLog("par_dir_fail"));
                        return false;
                    }
                }
            } catch (Exception e) {
                boolean success = directory.mkdir();
                if (success) {
                    logger.info(getLog("dir_created"));
                    return true;
                } else {
                    logger.severe(getLog("dir_failed"));
                    return false;
                }
            }
        }
        return false;
    }

    /***
     * Method to download file from url to path
     * @param url
     * @param destinationPath
     */
    public static void downloadFile(String url, String destinationPath) {
        logger.info("File from url *%url%* is loading.".replace("%url%", url));
        try {
            FileUtils.copyURLToFile(new URL(url), new File(destinationPath));
            logger.info("File from url *%url%* downloaded.".replace("%url%", url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
