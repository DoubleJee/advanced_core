package design_pattern.proxy.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GumballMachineRemote extends Remote {
    String getLocation() throws RemoteException;
    int getCount() throws RemoteException;
    String getState() throws RemoteException;
}
