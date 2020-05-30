package graph;

import edu.princeton.cs.algs4.Bag;

import java.util.Scanner;

/**
 * 图数据结构：使用邻接表实现
 */
public class Graph {
    private int V;                      //顶点个数
    private int E;                      //边条数
    Bag<Integer>[] adj;                 //邻接表
    public Graph(int v){
        this.V = v;this.E = 0;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(Scanner in){
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
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
