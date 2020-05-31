package graph;

import edu.princeton.cs.algs4.Bag;

import java.io.FileInputStream;
import java.util.Scanner;

/*
判断图是否有环
思路:如果存在顶点u的邻接点v，v的邻接点存在已标记且不等于u的点，则有环
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++) {
            if(!marked[v]) dfs(G,v,v);
        }
    }

    private void dfs(Graph G,int v, int u){
        marked[v] = true;
        for(int w:G.adj(v)){
            if(!marked[w])
                dfs(G,w,v);
            else if(w != u) hasCycle = true;
        }
    }

    public boolean hasCycle(){
        return  hasCycle;
    }

    public static void main(String[] args){
        try {
            Graph G = new Graph(new Scanner(new FileInputStream(args[0])));
            Cycle cycle = new Cycle(G);
            System.out.println("hasCycle:" + cycle.hasCycle);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
