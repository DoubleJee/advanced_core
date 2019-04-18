package design_pattern.proxy.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

//远程服务
public interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
}
