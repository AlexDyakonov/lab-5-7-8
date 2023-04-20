package server.services;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;

/**
 * The type Script manager.
 */
public class ScriptManager {
    private static final Logger logger = Logger.getLogger(ScriptManager.class.getName());
    private final List<String> scripts;


    /**
     * Instantiates a new Script manager.
     *
     * @param scripts the scripts
     */
    public ScriptManager(List<String> scripts) {
        setupLogger(logger);
        logger.info(getLog("script_manager_init_start"));
        this.scripts = scripts == null ? new ArrayList<>() : scripts;
        logger.info(getLog("script_manager_init_finish"));
    }

    /**
     * Add to scripts.
     *
     * @param script the script
     */
    public void addToScripts(String script) {
        scripts.add(script);
        logger.info(getLog("script_added_to_sm").replace("%script%", Paths.get(script).getFileName().toString()));
    }

    /**
     * Clear scripts.
     */
    public void clearScripts() {
        scripts.clear();
        logger.info(getLog("cleared_scripts"));
    }

    /**
     * Gets scripts.
     *
     * @return the scripts
     */
    public List<String> getScripts() {
        return scripts;
    }

}
