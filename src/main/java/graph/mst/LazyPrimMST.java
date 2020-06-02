package graph.mst;

import sort.MinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LazyPrimMST
 * 思路：取树与非树之间最小的横切边加入树
 * 方法：使用一个队列维护树种每个顶点的邻边加入队列中，从队列取最小的边
 */
public class LazyPrimMST implements MST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private Queue<Edge> pq ;
    public LazyPrimMST(EdgeWeighedGraph G){
        marked = new boolean[G.getV()];
        mst = new LinkedList();
        pq = new  PriorityQueue();
        visit(G,0);
        while (!pq.isEmpty()){
            Edge min = pq.poll();
            int v = min.either();
            int w = min.another(v);
            if(marked[v] && marked[w]) continue;
            mst.offer(min);
            if(marked[v]) visit(G,w);
            else visit(G,v);
        }
    }
    private void visit(EdgeWeighedGraph G,int v){
        marked[v] = true;
        for (Edge e: G.adj(v)){
            if(!pq.contains(e)) pq.offer(e);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public Double weight() {
        Double total = 0D;
        for (Edge e: mst){
            total += e.weight();
        }
        return total;
    }
}
