package graph.digraph;

import graph.sp.EdgeWeightedDigraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 有向图拓扑排序
 * 思路：首先检测有向图是否有环，有环则不存在拓扑排序
 * 深度优先遍历的逆后序即为：拓扑排序
 *
 * java.util 中的stack iterator 排序不会安装栈的先入后出？
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G){
        DirectedCycle dc = new DirectedCycle(G);
        if(dc.hasCycle()) return;
        else {
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.getReversePost();
        }
    }

    public Topological(EdgeWeightedDigraph G){
        DirectedCycle dc = new DirectedCycle(G);
        if(dc.hasCycle()) return;
        else {
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.getReversePost();
        }
    }
    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order(){
        return order;
    }
}
