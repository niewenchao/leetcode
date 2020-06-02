package graph;

import graph.digraph.Digraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

/**
 * 搜索与起始点的连通路径 支持无向图 有向图
 * dfs：深度优先遍历 路径长
 * bfs：广度优先遍历 路径最短
 * edgeTo:vn=vn-1= ...=v1=s
 */
public class Paths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public Paths(Graph G, int s){
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        bfs(G,s);
    }

    public Paths(Digraph G, int s){
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        bfs(G,s);
    }

    /*
     * 深度优先遍历连通路径
     * @param G
     * @param v
     */
    private void dfs(Graph G,int v){
        marked[v] = true;
        for (int w:G.adj(v)) {
            if(marked[w] == false){
                edgeTo[w] = v;
                dfs(G,w);
            }

        }
    }

    /**
     * 广度优先遍历连通路径，为最短路径
     * @param G
     * @param s
     */
    private void bfs(Graph G, int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w: G.adj(v)){
                if(marked[w] == false){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    private void bfs(Digraph G, int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w: G.adj(v)){
                if(marked[w] == false){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int w){
        return marked[w];
    }

    public Iterable<Integer> pathTo(int w){
        if(!hasPathTo(w)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int i = w; i != s ; i = edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args)
    {
        try {
            Graph G = new Graph(new Scanner(new FileInputStream(args[0])));
            int s = Integer.parseInt(args[1]);
            Paths search = new Paths(G, s);
            PrintStream StdOut =  System.out;
            for (int v = 0; v < G.getV(); v++) {
                StdOut.print(s + " to " + v + ": ");
                if (search.hasPathTo(v)){
                    Stack<Integer> tI = (Stack<Integer>)search.pathTo(v);
                    while (!tI.empty()){
                        int x = tI.pop();
                        if (x == s) StdOut.print(x);
                        else StdOut.print("-" + x);
                    }
                    StdOut.println();

                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
