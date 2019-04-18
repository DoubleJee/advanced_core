package design_pattern.proxy;

import design_pattern.proxy.remote.GumballMachineRemote;

import java.rmi.RemoteException;

public class GumballMachineMonitor {

    private GumballMachineRemote gumballMachineRemote;

    public GumballMachineMonitor(GumballMachineRemote gumballMachineRemote){
        this.gumballMachineRemote = gumballMachineRemote;
    }

    /**
     * 监视报告
     */
    public void report(){
        try {
            System.out.println("糖果机位置：" + gumballMachineRemote.getLocation());
            System.out.println("现在的库存：" + gumballMachineRemote.getCount() + "个糖果");
            System.out.println("现在的状态：" + gumballMachineRemote.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
