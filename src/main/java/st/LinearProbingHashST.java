package st;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashST<K,V> implements ST<K,V> {
    private K[] keys;
    private V[] vals;
    private int N;
    private int M;
    public LinearProbingHashST(){
        this(16);
    }

    public LinearProbingHashST(int cap){
        keys = (K[])new Object[cap];
        vals = (V[])new Object[cap];
        M = cap;
    }
    private int hash(K k){
        return (k.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int size){
        LinearProbingHashST st = new LinearProbingHashST(size);
        for (int i = 0; i < M; i++) {
            if(keys[i] != null) st.put(keys[i],vals[i]);
        }
        keys = (K[])st.keys;
        vals = (V[])st.vals;
        M = st.M;
    }

    public  void put(K k, V v) {
        if(N >= M/2) resize(2*M);

        int i;
        for (i = hash(k); keys[i] != null ; i = (i+1)%M) {
           if(k.equals(keys[i])) {vals[i] = v;return;}

        }
        keys[i] = k;vals[i] = v;N++;
    }
    public  void delete(K k){
        if(!contains(k)) return;
        int i = hash(k);
        while (!keys[i].equals(k))
            i = (i + 1) % M;
        keys[i] = null; vals[i] = null;
        i++;
        while (keys[i] != null){
            K tempk = keys[i];
            V tempv = vals[i];
            keys[i] = null; vals[i] = null;
            N--;
            put(tempk,tempv);
            i = (i + 1) % M;
        }
        N--;
        if(N > 0 && N < M/8) resize(M/2);
    }
    public  V get(K k){
        int i;
        for (i = hash(k); keys[i] != null ; i = (i+1)%M) {
            if(k.equals(keys[i])) return vals[i];
        }
        return null;
    }
    public  int size(){
        return N;
    }
    public  boolean isEmpty(){
        return N == 0;
    }
    public  boolean contains(K k){
        return get(k) != null;
    }
    public Iterable<K> keys(){
        List<K> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            if(keys[i] != null) list.add(keys[i]);
        }
        return list;
    }
}
