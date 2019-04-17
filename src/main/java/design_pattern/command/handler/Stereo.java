package design_pattern.command.handler;

public class Stereo {
    public void on(){
        System.out.println("打开音响！");
    }

    public void off(){
        System.out.println("关闭音响....");
    }

    public void setCD(){
        System.out.println("放入CD...");
    }

    public void setVolume(int volume){
        System.out.println("音量：" + volume);
    }
}
