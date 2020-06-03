package graph.sp;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 迪杰斯特拉最短路径算法
 * 思路：维持一个索引队列,每次取最小的顶点就行放松操作，放松操作会更新索引队列，直到索引为空，即找到了起点为S的最短路径树
 * 核心：relax函数
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;                  //每个顶点最短路径的最后一条边
    private double[] disTo;                         //每个顶点最短路径的权重
    private IndexMinPQ<Double> pq;                          //最小有优先级队列，用来选出最小的权重顶点
    public DijkstraSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.getV()];
        disTo = new double[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.getV());
        disTo[s] = 0.0;
        pq.insert(s,disTo[s]);
        while (!pq.isEmpty()){
            relax(G,pq.delMin());
        }
    }

    public void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e: G.adj(v)){
            int w = e.to();
            if(disTo[w] > disTo[v] + e.weight()){
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.change(w,disTo[w]);
                else pq.insert(w,disTo[w]);
            }
        }
    }

    public double disTo(int v){
        return disTo[v];
    }

    public boolean hasPathTo(int v){
        return disTo(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> edges = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            edges.push(e);
        }
        return edges;
    }

    public static void main(String[] args){
        try {
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(new Scanner(new FileInputStream(args[0])));
            int s = Integer.parseInt(args[1]);
            DijkstraSP sp = new DijkstraSP(G,s);
            for (int i = 0; i < G.getV(); i++) {
                System.out.print(s + " to " + i + "(" + String.format("%.2f", sp.disTo(i)) + ")" + ":");
                if(sp.hasPathTo(i)){
                    for (DirectedEdge e: sp.pathTo(i)){
                        System.out.print(" " + e);
                    }
                }
                System.out.println();

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

