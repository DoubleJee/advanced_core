package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.GarageDoor;

public class GarageDoorCloseCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorCloseCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        garageDoor.down();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        garageDoor.up();
    }
}
