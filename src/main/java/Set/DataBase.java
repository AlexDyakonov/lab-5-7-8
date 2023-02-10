package Set;

import model.HumanBeing;

import java.util.LinkedHashSet;
import java.util.Set;

public class DataBase {
    private final Set<HumanBeing> dataBase= new LinkedHashSet<>();

    public Set<HumanBeing> getDataBase() {
        return dataBase;
    }

    public Set<HumanBeing> addToDB(Set<HumanBeing> dataBase, HumanBeing humanBeing){
        dataBase.add(humanBeing);
        return dataBase;
    }

    public Set<HumanBeing> removeFromDB(Set<HumanBeing> dataBase, HumanBeing humanBeing){
        dataBase.remove(humanBeing);
        return dataBase;
    }
}
