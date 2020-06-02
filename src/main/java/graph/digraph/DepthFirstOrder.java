package graph.digraph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * 前序： 递归调用前将顶点加入队列
 * 后序： 递归调用后将顶点加入队列
 * 逆后序：递归调用后将顶点压入栈
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        marked = new boolean[G.getV()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) dfs(G,i);
        }
    }

    private void dfs(Digraph G,int v){
        marked[v] = true;
        pre.add(v);
        for(int w : G.adj(v)){
            if(!marked[w]) dfs(G,w);
        }
        post.add(v);
        reversePost.push(v);
    }

    public Queue<Integer> getPre() {
        return pre;
    }

    public Queue<Integer> getPost() {
        return post;
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
    }
}
