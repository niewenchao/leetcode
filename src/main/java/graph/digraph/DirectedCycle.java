package graph.digraph;

import graph.sp.DirectedEdge;
import graph.sp.EdgeWeightedDigraph;

import java.util.Stack;

/**
 * 有向图环检测
 * 思路：入栈的状态有交叉即为有环:一旦找到一条有向边v->w且w已经存在于栈中，就找到了一个环（因为栈的调用顺序是w->v）
 *       使用onStack保存栈的状态
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;                              //保存入栈状态

    public DirectedCycle(Digraph dg){
        marked = new boolean[dg.getV()];
        edgeTo = new int[dg.getV()];
        onStack = new boolean[dg.getV()];
        for (int i = 0; i < dg.getV(); i++) {
            if(!marked[i]) dfs(dg,i);
        }

    }

    public DirectedCycle(EdgeWeightedDigraph dg){
        marked = new boolean[dg.getV()];
        edgeTo = new int[dg.getV()];
        onStack = new boolean[dg.getV()];
        for (int i = 0; i < dg.getV(); i++) {
            if(!marked[i]) dfs(dg,i);
        }

    }

    private void dfs(Digraph G,int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if(hasCycle()) return;
            if(!marked[w])
            {   edgeTo[w] = v; dfs(G,w);    }
            else if(onStack[w]){
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }

        }
        onStack[v] = false;
    }

    private void dfs(EdgeWeightedDigraph G,int v){
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(hasCycle()) return;
            if(!marked[w])
            {   edgeTo[w] = v; dfs(G,w);    }
            else if(onStack[w]){
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }

        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }
}
