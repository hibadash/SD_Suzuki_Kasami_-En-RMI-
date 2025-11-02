package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import common.Request;
import common.Token;

public interface INode extends Remote {
    void receiveRequest(Request req) throws RemoteException;
    void receiveToken(Token token) throws RemoteException;
}
