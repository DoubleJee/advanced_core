package design_pattern.command.impl;

import design_pattern.command.Command;
import design_pattern.command.handler.Stereo;

public class StereoOffCommand implements Command {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo){
        this.stereo = stereo;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        stereo.off();
    }

    /**
     * 撤回命令
     */
    @Override
    public void undo() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
}
