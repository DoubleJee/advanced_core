package design_pattern.command.impl;

import design_pattern.command.Command;

public class GroupCommand implements Command {

    Command[] commands;

    public GroupCommand(Command[] commands){
        this.commands = commands;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        for (int i = 0;i < commands.length; i++){
            commands[i].execute();
        }
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--){
            commands[i].undo();
        }
    }
}
