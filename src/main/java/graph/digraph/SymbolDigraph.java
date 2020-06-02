package graph.digraph;

import graph.Graph;
import graph.Paths;
import graph.SymbolGraph;
import st.LinearProbingHashST;
import st.ST;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 符号有有向图，使用符号表（符号->索引） 数组维护（索引->符号）
 */
public class SymbolDigraph {
    private ST<String,Integer> st;                      //符号->索引
    private String[] keys;                              //索引->符号
    private Digraph graph;
    public SymbolDigraph(String stream, String split){
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
            graph = new Digraph(st.size());
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

    public Digraph G(){
        return graph;
    }

    /**
     * 测试案例
     * 测试有向符号图
     */
    public static void testSymbolDigraph(String[] args) {
        String filename = args[0];
        String spl = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, spl);
        Digraph G = sg.G();
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

    /**
     * 测试有向最短路径
     * @param args
     */
    public static void testDegreeOfSperation(String[] args){
        String filename = args[0];
        String spl = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, spl);
        Digraph G = sg.G();
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

    /**
     * 测试拓扑排序
     * 栈的迭代器不是先进后出？
     * @param args
     */
    public static void testTopological(String[] args){
        String filename = args[0];
        String spl = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, spl);
        Topological tp = new Topological(sg.graph);
        Stack<Integer> tps = (Stack)tp.order();
        while (!tps.isEmpty()){
            System.out.println(" " + sg.name(tps.pop()));
        }
    }

    public static void main(String[] args){

        //testSymbolDigraph(args);
        //testDegreeOfSperation(args);
        testTopological(args);

    }

}

