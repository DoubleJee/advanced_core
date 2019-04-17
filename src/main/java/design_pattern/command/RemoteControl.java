package design_pattern.command;

import design_pattern.command.impl.NoCommand;

public class RemoteControl {

    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;//最后一次执行的命令

    public RemoteControl(int slotCount){
        onCommands = new Command[slotCount];
        offCommands = new Command[slotCount];
        undoCommand = NoCommand.getInstance();
    }


    public void setCommand(int slot,Command onCommand,Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPressed(int slot){
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPressed(int slot){
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPressed(){
        undoCommand.undo();
        undoCommand = NoCommand.getInstance();
    }
}
