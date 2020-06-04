package graph.sp;

import edu.princeton.cs.algs4.Stack;
import graph.digraph.Topological;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 最长路径算法，跟SP基本一致，大于小于互换
 */
public class AcyclicLP {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    public AcyclicLP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.getV()];
        disTo = new double[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            disTo[i] = Double.MIN_VALUE;
        }
        disTo[s] = 0.0;
        Topological tp = new Topological(G);

        for(int i:tp.order()){
            relax(G,i);
        }
    }

    public void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e: G.adj(v)){
            int w = e.to();
            if(disTo[w] < disTo[v] + e.weight()){
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

    public static void CPM(String[] args){
        try {
            Scanner In = new Scanner(new FileInputStream(args[0]));
            int N = In.nextInt();In.nextLine();
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
            int s = 2*N , t = 2*N +1;
            for (int i = 0; i < N; i++) {
                String[] arr = In.nextLine().split("\\s+");
                double duration = Double.parseDouble(arr[0]);
                G.addEdge(new DirectedEdge(i,i+N,duration));
                G.addEdge(new DirectedEdge(s,i,0.0));
                G.addEdge(new DirectedEdge(i+N,t,0.0));
                for (int j = 1; j < arr.length; j++) {
                    G.addEdge(new DirectedEdge(i+N,Integer.parseInt(arr[j]),0.0));
                }

            }
            AcyclicLP lp = new AcyclicLP(G,s);
            System.out.println("start times:");
            for (int i = 0; i < N; i++) {
                System.out.printf("%4d: %5.1f\n", i, lp.disTo(i));
            }
            System.out.printf("Finish time: %5.1f\n", lp.disTo(t));

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    /**
     * 使用最长路径算法解决并行调度问题
     * @param args
     */
    public static void main(String[] args){
        CPM(args);
    }
}
