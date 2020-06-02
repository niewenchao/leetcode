package graph.mst;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 即时Prim算法
 * 思路：取树与非树之间最小的横切边加入树
 * 方法：维护生成树外的顶点到树的最小权重索引distTo与索引边edgeTo,并将该（顶点权重）加到队列，每次取队列最小顶点加入到生成树中，并更新，
 *      直到队列为空，此时edgeTo即为最小生成树
 */
public class PrimMST implements MST {
    private boolean[] marked;
    private Edge[] edgeTo;          //顶点最小权重边
    private Double[] distTo;       //顶点最小权重
    private IndexMinPQ<Double> pq; //索引队列

    public PrimMST(EdgeWeighedGraph G){
        marked = new boolean[G.getV()];
        edgeTo = new Edge[G.getV()];
        distTo = new Double[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
           distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.getV());
        distTo[0] = 0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }

    private void visit(EdgeWeighedGraph G, int v){
        marked[v] = true;
        for (Edge e: G.adj(v)){
            int w = e.another(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]){
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.changeKey(w,e.weight());
                else pq.insert(w,e.weight());
            }

        }
    }

    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> mst = new Bag<>();
        for (int i = 1; i < edgeTo.length; i++) {
            mst.add(edgeTo[i]);
        }
        return mst;
    }

    @Override
    public Double weight() {
        Double total = 0.0;
        for (int i = 1; i < edgeTo.length; i++) {
            total += edgeTo[i].weight();
        }
        return total;
    }
}
