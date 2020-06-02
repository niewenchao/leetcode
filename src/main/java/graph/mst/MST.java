package graph.mst;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 最小生成树接口
 */
public interface MST {
    public Iterable<Edge> edges();
    public Double weight();

    public static void main(String[] args){
        try {
            EdgeWeighedGraph G = new EdgeWeighedGraph(new Scanner(new FileInputStream(args[0])));
            MST mst = new LazyPrimMST(G);
            for (Edge e:mst.edges()){
                System.out.println(e);
            }
            System.out.println(mst.weight());

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

