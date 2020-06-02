package graph.digraph;

import edu.princeton.cs.algs4.Bag;

import java.util.Scanner;

/**
 * 有向图
 */
public class Digraph {
    private int V;                      //顶点个数
    private int E;                      //边条数
    Bag<Integer>[] adj;                 //邻接表
    public Digraph(int v){
        this.V = v;this.E = 0;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(Scanner in){
        this(in.nextInt());
        int tE = in.nextInt();
        for (int i = 0; i < tE; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v,w);
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph rev = new Digraph(V);
        for (int w = 0; w < V; w++) {
            for(int v:adj(w)){
                rev.addEdge(v,w);
            }

        }
        return rev;
    }
}
