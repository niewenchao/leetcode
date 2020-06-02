package graph.digraph;

import edu.princeton.cs.algs4.Bag;

/**
 * 顶点的有向连通性
 */
public class DirectedDFS {
    private boolean[] marked;
    public DirectedDFS(Digraph dg, int s){
        marked = new boolean[dg.getV()];
        dfs(dg,s);
    }

    public DirectedDFS(Digraph dg, Bag<Integer> sources){
        marked = new boolean[dg.getV()];
        for(int s:sources){
            if(!marked[s]) dfs(dg,s);
        }
    }

    private void dfs(Digraph dg, int s){
        marked[s] = true;
        for (int w:dg.adj(s)){
            if(!marked[w]) dfs(dg,w);
        }
    }

    public boolean marked(int v){
        return marked[v];
    }
}
