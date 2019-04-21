package design_pattern.proxy;


import design_pattern.proxy.remote.GumballMachineRemote;
import design_pattern.proxy.remote.MyRemote;
import design_pattern.proxy.remote.impl.MyRemoteImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyTest {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//        MyRemote myRemote = (MyRemote) Naming.lookup("rmi://localhost/HelloService");
//        lookup返回的是一个stub代理类，实现MyRemote类，所以不能用实现类来接受
//        System.out.println(myRemote.sayHello());

        //远程代理

        //lookup返回stub代理类，继承GumballMachineRemote接口，转换成接口
        GumballMachineRemote shanghaiGumballMachine = (GumballMachineRemote) Naming.lookup("rmi://localhost/ShanghaiGumballMachine");
        GumballMachineRemote heFeiGumballMachine = (GumballMachineRemote) Naming.lookup("rmi://localhost/HeFeiGumballMachine");

        GumballMachineMonitor shanghaiGumballMachineMonitor = new GumballMachineMonitor(shanghaiGumballMachine);
        GumballMachineMonitor heFeiGumballMachineMonitor = new GumballMachineMonitor(heFeiGumballMachine);
        shanghaiGumballMachineMonitor.report();
        heFeiGumballMachineMonitor.report();

        //代理模式：为一个对象提供一个替身或者占位符，以控制对这个对象的访问。（让代理对象控制某对象的访问吗，代理作为另一个对象的代表）


        // 监视器（客户） -> 糖果机代理（proxy） -> 真实糖果机（realSubject）
    }
}
