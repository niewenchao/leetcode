package graph.digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import graph.CC;
import graph.Graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 有向图中的强连通性
 * 解决 1、点与点是否强连通？2、图中有几个强连通分量？之类问题
 * 思路：使用DepthFirstOrder取GR(G的逆图)的逆后排序；用该排序堆G进行深度优先搜索，可以保证每次递归都在同一个强连通分量中
 */
public class SCC {
    private boolean[] marked;           //标记
    private int[] id;                   //顶点所在连通分量
    private int count;                  //连通分量个数
    public SCC(Digraph G){
        this.marked = new boolean[G.getV()];
        this.id = new int[G.getV()];
        /*
        使用DepthFirstOrder取GR(G的逆图)的逆后排序
         */
        Stack<Integer> so = new DepthFirstOrder(G.reverse()).getReversePost();
        /*
        使用该排序进行深度优先搜索
         */
        while (!so.isEmpty()){
            int v = so.pop();
            System.out.println(" " + v);
            if(!marked[v])  {dfs(G,v);count++;}

        }
    }
    private void  dfs(Digraph G,int v){
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if(!marked[w]) dfs(G,w);
        }
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public static void main(String[] args){
        try {
            Digraph dg = new Digraph(new Scanner(new FileInputStream(args[0])));
            SCC scc = new SCC(dg);
            Bag<Integer>[] components = new Bag[scc.count];
            for (int i = 0; i < scc.count(); i++) {
                components[i] = new Bag<>();
            }
            for (int i = 0; i < dg.getV(); i++) {
                components[scc.id(i)].add(i);
            }

            System.out.println(scc.count() + " components");
            for (int i = 0; i < scc.count(); i++) {
                for (int v:components[i]){
                    System.out.print(" "+ v);
                }
                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
