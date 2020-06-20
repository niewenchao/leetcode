package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenLock {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>();
        for(String dead: deadends) deads.add(dead);
        int step = 0;
        queue.offer("0000");
        visited.add("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                //终止条件
                if(deads.contains(cur)) continue;
                if(cur.equals(target)) return step;
                //adj
                for(int j = 0; j < 4; j++ ){
                    String plus = plusOne(cur, j);
                    if(!visited.contains(plus)){
                        queue.offer(plus);
                        visited.add(plus);
                    }
                    String minus = minusOne(cur, j);
                    if(!visited.contains(minus)){
                        queue.offer(minus);
                        visited.add(minus);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String plusOne(String str, int i){
        char[] ch = str.toCharArray();
        if(ch[i] == '9')
            ch[i] = '0';
        else
            ch[i] += 1;
        return new String(ch);
    }

    private String minusOne(String str, int i){
        char[] ch = str.toCharArray();
        if(ch[i] == '0')
            ch[i] = '9';
        else
            ch[i] -= 1;
        return new String(ch);
    }

    public static void main(String[] args){
        OpenLock ol = new OpenLock();
        String[] s = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(ol.openLock(s,target));
    }
}
