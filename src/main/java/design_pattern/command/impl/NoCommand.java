package design_pattern.command.impl;

import design_pattern.command.Command;

public class NoCommand implements Command {

    private static NoCommand noCommand;

    private NoCommand(){

    }

    public static final NoCommand getInstance(){
        if(noCommand == null){
            synchronized (Command.class){
                if(noCommand == null){
                    noCommand = new NoCommand();
                }
            }
        }
        return noCommand;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {

    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {

    }
}
