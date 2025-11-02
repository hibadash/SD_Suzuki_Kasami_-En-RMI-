package server;

import interfaces.IStock;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockServer {
    public static void main(String[] args) {
        try {
            IStock stock = new StockImpl(5);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("StockService", stock);
            System.out.println("[SERVER] Stock RMI prêt !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
