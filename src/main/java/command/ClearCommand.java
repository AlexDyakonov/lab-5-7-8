package command;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import services.HumanBeingServiceImpl;

public class ClearCommand implements Command{
    private static final HumanBeingControllerImpl userController = new HumanBeingControllerImpl(new HumanBeingServiceImpl(new HumanBeingDAOImpl()));

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(String[] args) {
        userController.clear();
    }
}