package graph.mst;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.MinPQ;

/**
 * KruksalMST
 * 思路：每次取权重最小的边加入mst，边的两个顶点如果已经在树中则跳过
 */
public class KruskalMST implements MST{
    private Bag<Edge> mst;
    private boolean[] marked;
    public KruskalMST(EdgeWeighedGraph G){
        marked = new boolean[G.getV()];
        mst = new Bag<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e: G.edges()){
            pq.insert(e);
        }

        while (!pq.isEmpty() && mst.size() < G.getV() -1){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.another(v);
            if(marked[v] && marked[w]) continue;
            mst.add(e);
            marked[v] = true;
            marked[w] = true;
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
