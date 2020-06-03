package graph.sp;

import edu.princeton.cs.algs4.Bag;
import java.util.Scanner;

/**
 * 加权有向图
 */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    public EdgeWeightedDigraph(int v){
        this.V = v;
        this.adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(Scanner in){
        this(in.nextInt());
        int tE = in.nextInt();
        for (int i = 0; i < tE; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            double weight = in.nextDouble();
            addEdge(new DirectedEdge(v,w,weight) );
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> queue = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e:adj(i)){
                queue.add(e);
            }
        }
        return queue;
    }
}
