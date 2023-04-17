package server.services;

import java.util.ArrayList;
import java.util.List;

public class ScriptManager {
    private List<String> scripts = new ArrayList<String>();

    public ScriptManager(List<String> scripts) {
        this.scripts = scripts == null ? new ArrayList<>() : scripts;
    }

    public void addToScripts(String script) {
        scripts.add(script);
    }

    public void clearScripts() {
        scripts.clear();
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }
}
