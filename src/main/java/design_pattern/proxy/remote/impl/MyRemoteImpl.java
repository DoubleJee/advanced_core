package design_pattern.proxy.remote.impl;

import design_pattern.proxy.remote.MyRemote;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() {
        return "server says, 'Hey'";
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        MyRemote myRemote = new MyRemoteImpl();
        Naming.bind("HelloService",myRemote);
    }
}
