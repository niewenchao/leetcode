package graph.mst;

import edu.princeton.cs.algs4.Bag;

import java.util.Scanner;

/**
 * 最小生成树权重图结构
 */
public class EdgeWeighedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    public EdgeWeighedGraph(int v){
        this.V = v;
        this.adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeighedGraph(Scanner in){
        this(in.nextInt());
        int tE = in.nextInt();
        for (int i = 0; i < tE; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            double weight = in.nextDouble();
            addEdge(new Edge(v,w,weight) );
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.another(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> queue = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (Edge e:adj(i)){
                int v= e.either();
                int w = e.another(v);
                if(v < w) queue.add(e);
            }
        }
        return queue;
    }
}
