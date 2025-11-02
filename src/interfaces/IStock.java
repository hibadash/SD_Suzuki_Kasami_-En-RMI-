package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStock extends Remote {
    void addCarton() throws RemoteException;
    void removeCarton() throws RemoteException;
    int getCurrent() throws RemoteException;
    int getCapacity() throws RemoteException;
}
