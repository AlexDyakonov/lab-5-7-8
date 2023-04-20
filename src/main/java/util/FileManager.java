package util;

import org.codehaus.plexus.util.FileUtils;
import server.exception.ApplicationException;
import server.exception.FileException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        } catch (Exception e) {
            throw new FileException("File was not downloaded and not exist. ", e);
        }
    }

    /**
     * Save method to check is file on local machine the same with newest version of texts.
     *
     * @param url
     * @param destinationPath
     */
    public static void updateFile(String url, String destinationPath) {
        if (!Files.exists(Paths.get(destinationPath))) {
            downloadFile(url, destinationPath);
        } else if (!checkFileContent(url, destinationPath)) {
            logger.info("File *%file%* exist and not the same.".replace("%file%", destinationPath));
            downloadFile(url, destinationPath);
        } else {
            logger.info("File is same.");
        }
    }

    /**
     * Checks two file and their content. If same returns true
     *
     * @param url
     * @param localFilePath
     * @return boolean
     */
    public static boolean checkFileContent(String url, String localFilePath) {
        try {
            File urlContent = getContentFromUrl(url);
            File localContent = new File(localFilePath);
            return FileUtils.contentEquals(localContent, urlContent);
        } catch (IOException e) {
            throw new FileException("Files wasnt created", e);
        }
    }

    /**
     * Download file from url to temp file on local machine.
     *
     * @param url
     * @return
     */
    private static File getContentFromUrl(String url) {
        try {
            File temp = File.createTempFile("contentFromUrl", ".tmp");
            downloadFile(url, temp.toPath().toString());
            temp.deleteOnExit();
            return new File(temp.toPath().toString());
        } catch (IOException e) {
            throw new FileException("Temp file error.", e);
        }
    }
}
