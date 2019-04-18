package design_pattern.proxy;

import design_pattern.proxy.remote.GumballMachineRemote;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

//糖果机
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
    /**
     * 位置
     */
    String location;
    /**
     * 数量
     */
    int count;
    /**
     * 状态
     */
    String state = "等待入驻";

    public GumballMachine(String location, int count) throws RemoteException {
        this.location = location;
        this.count = count;
    }

    public String getLocation() throws RemoteException {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() throws RemoteException {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() throws RemoteException {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        GumballMachineRemote shanghaiGumballMachine = new GumballMachine("上海", 3);
        GumballMachineRemote heFeiGumballMachine = new GumballMachine("合肥", 200);
        //RMI 仓库（注册表）
        LocateRegistry.createRegistry(1099);
        Naming.bind("ShanghaiGumballMachine", shanghaiGumballMachine);
        Naming.bind("HeFeiGumballMachine", heFeiGumballMachine);
    }
}
