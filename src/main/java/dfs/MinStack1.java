package dfs;

import java.util.Stack;

/**
 * 时间o(1) 空间 o(1)
 */
public class MinStack1 {
    private Stack<Integer> s;
    private int min = 0;

    public MinStack1() {
        s = new Stack();
    }

    public void push(int x) {
        if(s.isEmpty()){
            s.push(0);
            min = x;
        }
        else{
            int compare = x - min;
            s.push(compare);
            min = compare < 0 ? x : min;
        }

    }

    public void pop() {
        int top = s.pop();
        min = top < 0 ? (min - top) : min;
    }

    public int top() {
        int compare = s.peek();
        if(compare < 0) return min;
        else return (min + compare);
    }

    public int getMin() {
        return min;
    }

    public static  void  main(String[] args){
        MinStack1 s = new MinStack1();
        s.push(2147483646);
        s.push(2147483646);
        s.push(2147483647);
        System.out.println(s.top());
        s.pop();
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());
        s.pop();
        s.push(2147483647);
        System.out.println(s.top());
        System.out.println(s.getMin());
        s.push(-2147483648);
        System.out.println(s.top());

        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());








    }
}
