package graph;

import st.LinearProbingHashST;
import st.ST;
import st.SequentialSearchST;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


/**
 * 符号图，使图算法更通用
 * 思路使用符号表ST 映射了符号->索引的关系，数组映射了索引->符号的关系，需要读两遍文件，第一遍构造ST，并得到顶点个数初始化数组索引，第二遍构造索引图
 * 测试案例：
 * 1、testSymbolGraph
 *    测试了符号图相邻任意顶点
 * 2、testDegreeOfSperatition 任意点与输入起始点的度数
 * 使用路径算法Paths的bfs版本，实现的最短路径相当于度的路径
 */
public class SymbolGraph {
    private ST<String,Integer> st;                      //符号->索引
    private String[] keys;                              //索引->符号
    private Graph graph;
    public SymbolGraph(String stream, String split){
        st = new LinearProbingHashST<>();
        try {
            Scanner scan = new Scanner(new FileInputStream(stream));
            /*
            符号->索引
             */
            while (scan.hasNextLine()){
                String[] lines = scan.nextLine().split(split);
                for(String vn:lines){
                    if(!st.contains(vn)) st.put(vn,st.size());
                }
            }
            keys = new String[st.size()];
            /*
            索引->符号
             */
            for(String vn:st.keys()){
                keys[st.get(vn)] = vn;
            }
            /*
            读取第二遍，构造图
             */
            graph = new Graph(st.size());
            scan = new Scanner(new FileInputStream(stream));
            /*
            每行的第一个符号与其他符号构成边
             */
            while (scan.hasNextLine()){
                String[] lines = scan.nextLine().split(split);
                int v = st.get(lines[0]);
                for (int w = 1; w < lines.length; w++) {
                    graph.addEdge(v,st.get(lines[w]));
                }
            }
            scan.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean contains(String name){
        return st.contains(name);
    }

    public int index(String name){
        Object o = st.get(name);
        if(o == null) return -1;
        return (int)o;
    }

    public String name(int index){
        return keys[index];
    }

    public Graph G(){
        return graph;
    }

    /**
     * 测试案例
     * 测试符号图
     */
    public static void testSymbolGraph(String[] args) {
        String filename = args[0];
        String spl = args[1];
        SymbolGraph sg = new SymbolGraph(filename, spl);
        Graph G = sg.G();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            int id = sg.index(word);
            if (id == -1) {
                System.out.println(" 不存在");
                continue;
            }
            for (int w : G.adj(id)) {
                System.out.println(" " + sg.name(w));
            }
        }
    }

    public static void testDegreeOfSperation(String[] args){
        String filename = args[0];
        String spl = args[1];
        SymbolGraph sg = new SymbolGraph(filename, spl);
        Graph G = sg.G();
        String source = args[2];                            //起点
        if(sg.index(source) == -1)                 {System.out.println("起点不存在");return;}

        Paths path = new Paths(G,sg.index(source));

        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            int id = sg.index(word);
            if (id == -1) {
                System.out.println("终点不存在");
                continue;
            }
            if(!path.hasPathTo(id)) {System.out.println("路径不存在");continue;}
            Stack<Integer> tI = (Stack<Integer>)path.pathTo(id);
            while (!tI.empty()){
                int x = tI.pop();
                System.out.println(sg.name(x));
            }




        }
    }

    public static void main(String[] args){

       //testSymbolGraph(args);
        testDegreeOfSperation(args);

    }

}
