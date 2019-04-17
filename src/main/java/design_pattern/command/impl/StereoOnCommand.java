package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Stereo;

public class StereoOnCommand implements Command {

    private Stereo stereo;

    public StereoOnCommand(Stereo stereo){
        this.stereo = stereo;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        stereo.off();
    }
}
