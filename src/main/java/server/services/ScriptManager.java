package server.services;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static server.services.LoggerManager.setupLogger;
import static util.Message.getLog;

public class ScriptManager {
    private List<String> scripts;
    private static final Logger logger = Logger.getLogger(ScriptManager.class.getName());


    public ScriptManager(List<String> scripts) {
        setupLogger(logger);
        logger.info(getLog("script_manager_init_start"));
        this.scripts = scripts == null ? new ArrayList<>() : scripts;
        logger.info(getLog("script_manager_init_finish"));
    }

    public void addToScripts(String script) {
        scripts.add(script);
        logger.info(getLog("script_added_to_sm").replace("%script%", Paths.get(script).getFileName().toString()));
    }

    public void clearScripts() {
        scripts.clear();
        logger.info(getLog("cleared_scripts"));
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }
}
