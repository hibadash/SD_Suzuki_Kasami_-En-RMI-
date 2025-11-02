package client;

import interfaces.INode;
import interfaces.IStock;
import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class NodeLivraison extends UnicastRemoteObject implements INode {
    private int id;
    private int N;
    private int[] RN;
    private Token token;
    private boolean hasToken;
    private IStock stock;
    private Map<Integer, INode> others;

    public NodeLivraison(int id, int N, IStock stock) throws RemoteException {
        this.id = id;
        this.N = N;
        this.stock = stock;
        this.RN = new int[N];
        this.others = new HashMap<>();
        this.hasToken = false;
    }

    public void register(int id, INode node) {
        others.put(id, node);
    }

    public void requestCS() throws RemoteException {
        RN[id]++;
        if (!hasToken) {
            Request req = new Request(id, RN[id]);
            for (INode node : others.values()) node.receiveRequest(req);
        } else enterCS();
    }

    public void enterCS() throws RemoteException {
        System.out.println("[LIVRAISON] Section critique: retrait d’un carton...");
        stock.removeCarton();
        exitCS();
    }

    public void exitCS() throws RemoteException {
        token.LN[id] = RN[id];
        for (int j = 0; j < N; j++) {
            if (RN[j] == token.LN[j] + 1 && !token.Q.contains(j))
                token.Q.add(j);
        }
        if (!token.Q.isEmpty()) {
            int next = token.Q.poll();
            others.get(next).receiveToken(token);
            hasToken = false;
            System.out.println("[LIVRAISON] Jeton envoyé à " + next);
        }
    }

    @Override
    public void receiveRequest(Request req) throws RemoteException {
        RN[req.nodeId] = Math.max(RN[req.nodeId], req.requestNumber);
        if (hasToken && RN[req.nodeId] == token.LN[req.nodeId] + 1) {
            token.Q.add(req.nodeId);
            int next = token.Q.poll();
            others.get(next).receiveToken(token);
            hasToken = false;
        }
    }

    @Override
    public void receiveToken(Token token) throws RemoteException {
        this.token = token;
        this.hasToken = true;
        enterCS();
    }
}
