package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/*
判断图是否是二分图，等价于着色所有顶点使相邻的顶点颜色都不相同
思路：深度遍历邻接点，如果存在被标记过的u和其邻接点w颜色相同，则不是二分图，否则是
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] colors;
    private boolean isTwoColorable = true;
    public TwoColor(Graph G){
        marked = new boolean[G.getV()];
        colors = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++) {
            if(!marked[v]) dfs(G,v);
        }
    }

    private void dfs(Graph G,int s){
        marked[s] = true;
        for(int w:G.adj(s)){
            if(!marked[w])
            {colors[w] = !colors[s];dfs(G,w);}
            else if(colors[w] == colors[s]) isTwoColorable = false;
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }

    public static void main(String[] args){
        try {
            Graph G = new Graph(new Scanner(new FileInputStream(args[0])));
            TwoColor color = new TwoColor(G);
            System.out.println("isTwoColor:" + color.isBipartite());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
