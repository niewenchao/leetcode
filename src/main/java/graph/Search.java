package graph;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 搜索起始点的连通点
 * 被标记过的即为与起始点连通的点
 */
public class Search {
    private boolean[] marked;
    private int count;

    public Search(Graph G, int s){
        marked = new boolean[G.getV()];
        dfs(G,s);
    }

    /*
     * 深度优先遍历连通顶点
     * @param G
     * @param v
     */
    private void dfs(Graph G,int v){
        marked[v] = true;
        count++;
        for (int w:G.adj(v)) {
            if(marked[w] == false) dfs(G,w);
        }
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }


    /**
     * test
     */
    public static void main(String[] args){
        try {
            Scanner scan = new Scanner(new FileInputStream(args[0]));
            Graph G = new Graph(scan);
            int s = Integer.parseInt(args[1]);
            Search search = new Search(G,s);
            for (int v = 0; v < G.getV(); v++)
            if (search.marked(v))
                System.out.print(v + " ");
            System.out.println();
            if (search.count() != G.getV())
                System.out.print("NOT ");
            System.out.println("connected");

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
