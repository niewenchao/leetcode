package graph;

import edu.princeton.cs.algs4.Bag;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 连通分量
 * 思路：对图所有未标记点进行深度遍历，一次深度遍历代表一个分量，深度遍历的所有顶点id存储分量值
 */
public class CC {
    private boolean[] marked;           //标记
    private int[] id;                   //顶点所在连通分量
    private int count;                  //连通分量个数
    public CC(Graph G){
        this.marked = new boolean[G.getV()];
        this.id = new int[G.getV()];
        for (int s = 0; s < G.getV(); s++) {
            if(!marked[s])  {dfs(G,s);count++;}
        }
    }
    private void  dfs(Graph G,int v){
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
            Graph G = new Graph(new Scanner(new FileInputStream(args[0])));
            CC C = new CC(G);
            Bag<Integer>[] components = new Bag[C.count()];
            for (int i = 0; i < C.count(); i++) {
                components[i] = new Bag<>();
            }
            for (int i = 0; i < G.getV(); i++) {
                components[C.id(i)].add(i);
            }

            System.out.println(C.count() + " components");
            for (int i = 0; i < C.count(); i++) {
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
