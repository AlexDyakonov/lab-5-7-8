package utility;

import model.HumanBeing;
import model.HumanBeingResponseDTO;

import java.util.Set;

public interface FileManager {
    void save(Set<HumanBeing> humanBeingSet, String fileName);
    Set<HumanBeing> load(String path);
}
