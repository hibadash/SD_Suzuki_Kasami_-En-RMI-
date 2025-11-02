package common;

import java.io.Serializable;

public class Request implements Serializable {
    public int nodeId;
    public int requestNumber;

    public Request(int nodeId, int requestNumber) {
        this.nodeId = nodeId;
        this.requestNumber = requestNumber;
    }
}
