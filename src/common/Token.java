package common;

import java.io.Serializable;
import java.util.*;

public class Token implements Serializable {
    public int[] LN;
    public Queue<Integer> Q;

    public Token(int n) {
        LN = new int[n];
        Q = new LinkedList<>();
    }
}
