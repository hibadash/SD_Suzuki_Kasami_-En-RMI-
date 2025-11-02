package client;

import interfaces.IStock;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IStock stock = (IStock) registry.lookup("StockService");

            NodeProduction prod = new NodeProduction(0, 2, stock);
            NodeLivraison liv = new NodeLivraison(1, 2, stock);

            prod.register(1, liv);
            liv.register(0, prod);

            prod.requestCS(); // production ajoute un carton
            Thread.sleep(3000);
            liv.requestCS();  // livraison retire un carton

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
