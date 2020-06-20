package dfs;
import java.util.*;

/**
 * 最小栈，利用辅助栈维护最小元素
 */
public class MinStack {
    private Stack<Integer> s;
    private Stack<Integer> h;

    public MinStack() {
        s = new Stack();
        h = new Stack();
    }

    public void push(int x) {
        s.push(x);
        if(h.isEmpty())
            h.push(x);
        else
        {
            int top = h.peek();
            if(x <= top)
                h.push(x);
            else
                h.push(top);
        }
    }

    public void pop() {
        s.pop();
        h.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return h.peek();
    }
}


