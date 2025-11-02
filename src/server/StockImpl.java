package server;

import interfaces.IStock;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StockImpl extends UnicastRemoteObject implements IStock {
    private int capacity;
    private int current;

    public StockImpl(int capacity) throws RemoteException {
        this.capacity = capacity;
        this.current = 0;
    }

    @Override
    public synchronized void addCarton() throws RemoteException {
        if (current < capacity) {
            current++;
            System.out.println("[STOCK] +1 carton ajouté. Total = " + current);
        } else {
            System.out.println("[STOCK] Plein !");
        }
    }

    @Override
    public synchronized void removeCarton() throws RemoteException {
        if (current > 0) {
            current--;
            System.out.println("[STOCK] -1 carton retiré. Le total = " + current);
        } else {
            System.out.println("[STOCK] Vide !");
        }
    }

    @Override
    public int getCurrent() throws RemoteException {
        return current;
    }

    @Override
    public int getCapacity() throws RemoteException {
        return capacity;
    }
}
