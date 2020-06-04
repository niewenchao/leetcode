package graph.sp;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import graph.digraph.Topological;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 比迪杰斯特拉最短路径算法更快的加权无向图最短路径算法，可以负权重，是在已知加权无环图找出最短路径的最好方法
 * 思路：使用拓扑排序顺序对图进行relax，能够处理负权重
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.getV()];
        disTo = new double[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        disTo[s] = 0.0;
        Topological tp = new Topological(G);

        for(int i:tp.order()){
            System.out.print(i);
            relax(G,i);
        }
        System.out.println();
    }

    public void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e: G.adj(v)){
            int w = e.to();
            if(disTo[w] > disTo[v] + e.weight()){
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
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
            AcyclicSP sp = new AcyclicSP(G,s);
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
