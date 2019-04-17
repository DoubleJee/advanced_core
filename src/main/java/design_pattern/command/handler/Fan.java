package design_pattern.command.handler;

public class Fan {

    private int speed = -1;


    public void high(){
        speed = 1;
        System.out.println("风扇：高速档");
    }

    public void low(){
        speed = 0;
        System.out.println("风扇：低速档");
    }

    public void off(){
        speed = -1;
        System.out.println("风扇：关闭");
    }

    public int getSpeed(){
        return speed;
    }
}
