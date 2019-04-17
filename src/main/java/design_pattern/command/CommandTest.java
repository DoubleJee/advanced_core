package design_pattern.command;

import design_pattern.command.handler.Fan;
import design_pattern.command.handler.GarageDoor;
import design_pattern.command.handler.Light;
import design_pattern.command.handler.Stereo;
import design_pattern.command.impl.*;

public class CommandTest {

    public static void main(String[] args) {
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        Stereo stereo = new Stereo();
        Fan fan = new Fan();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        Command garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        Command garageDoorCloseCommand = new GarageDoorCloseCommand(garageDoor);
        Command stereoOffCommand = new StereoOffCommand(stereo);
        Command stereoOnCommand = new StereoOnCommand(stereo);
        Command fanHighCommand = new FanHighCommand(fan);
        Command fanLowCommand = new FanLowCommand(fan);
        Command fanOffCommand = new FanOffCommand(fan);
        RemoteControl remoteControl = new RemoteControl(6);
        remoteControl.setCommand(0,lightOnCommand,lightOffCommand);
        remoteControl.setCommand(1,garageDoorOpenCommand,garageDoorCloseCommand);
        remoteControl.setCommand(2,stereoOnCommand,stereoOffCommand);
        remoteControl.setCommand(3,fanHighCommand,fanOffCommand);
        remoteControl.setCommand(4,fanLowCommand,fanOffCommand);
        remoteControl.onButtonWasPressed(0);
        remoteControl.onButtonWasPressed(1);
        remoteControl.onButtonWasPressed(2);
        remoteControl.offButtonWasPressed(0);
        remoteControl.offButtonWasPressed(1);
        remoteControl.offButtonWasPressed(2);
        //撤销
        remoteControl.undoButtonWasPressed();

        remoteControl.onButtonWasPressed(3);
        remoteControl.onButtonWasPressed(4);
        remoteControl.offButtonWasPressed(3);
        remoteControl.undoButtonWasPressed();


        System.out.println("分界线--------------------------------");
        GroupCommand onGroupCommand = new GroupCommand(new Command[]{garageDoorOpenCommand, lightOnCommand, stereoOnCommand});
        GroupCommand offGroupCommand = new GroupCommand(new Command[]{stereoOffCommand,lightOffCommand,garageDoorCloseCommand});
        remoteControl.setCommand(5,onGroupCommand,offGroupCommand);
        remoteControl.onButtonWasPressed(5);
        remoteControl.offButtonWasPressed(5);
        remoteControl.undoButtonWasPressed();

        //命令模式：将请求封装成对象，这可以让你使用不同请求、队列，或者日志请求来参数化其他对象，命令模式也可以支持撤销操作。

    }
}
