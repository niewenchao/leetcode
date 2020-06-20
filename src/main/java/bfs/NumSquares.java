package bfs;

import java.util.*;

/**
 * 求n的最少完全平方数
 */
public class NumSquares {
    public int numSquares(int n) {
        //可能的组成元素
        Vector<Integer> v = new Vector();
        for(int i = 1; i*i <= n; i++){
            v.insertElementAt(i*i,0);
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int step = 0;
        q.offer(0);
        while(true){
            int s = q.size();
            int i = 0;
            for(; i < s; i++){
                int cur = q.poll();
                if(cur == n) break;
                for(int e : v){
                    if((cur + e) <= n && !visited.contains(cur + e)){
                        q.offer(cur + e);
                        visited.add(cur + e);

                    }
                }
            }
            if(i != s) break;
            step++;

        }
        return step;
    }

    public static void main(String[] args){
        System.out.println(new NumSquares().numSquares(7168));
    }
}
