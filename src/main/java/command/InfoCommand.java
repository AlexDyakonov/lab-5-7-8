package command;

import controller.HumanBeingControllerImpl;
import dao.HumanBeingDAOImpl;
import services.HumanBeingServiceImpl;

public class InfoCommand implements Command{
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
        System.out.println(userController.info());
    }
}
