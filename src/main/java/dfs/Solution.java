package dfs;
import java.util.*;
public class Solution {
    /**
     * 是否为有效字符串
     * @param s
     * @return
     */
    static public boolean isValid(String s){
        if(s.isEmpty()) return true;
        Stack<Character> stk = new Stack();
        for(char ch : s.toCharArray()){
            if(ch == '(') stk.push(')');
            else if(ch == '{') stk.push('}');
            else if(ch == '[') stk.push(']');
            else if(stk.isEmpty()  || ch != stk.pop()){
                return false;
            }
        }
        if(stk.isEmpty()) return true;
        else return false;
    }

    /**
     * 每日温度 维护温度单调递减的索引
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

     例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

     提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */
    public static int[] dailyTemperatures(int[] T) {
        Stack<Integer> sk = new Stack();
        int[] res = new int[T.length];
        for(int i = 0; i < T.length; i++){
            if(sk.isEmpty()) sk.push(i);
            else if(T[i] <= T[sk.peek()]){
                sk.push(i);
            }else{
                while(!sk.isEmpty() && T[i] > T[sk.peek()]){
                    int index = sk.pop();
                    res[index] = i - index;
                }
                sk.push(i);
            }
        }
        return res;
    }

    /**
     *
     * 根据逆波兰表示法，求表达式的值。

     有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

     说明：

     整数除法只保留整数部分。
     给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     示例 1：

     输入: ["2", "1", "+", "3", "*"]
     输出: 9
     解释: ((2 + 1) * 3) = 9
     示例 2：

     输入: ["4", "13", "5", "/", "+"]
     输出: 6
     解释: (4 + (13 / 5)) = 6
     *
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> sk = new Stack<>();
        String dic = "+-*/";
        for(String s : tokens){
            if(dic.contains(s)){
                int l2 = Integer.parseInt(sk.pop());
                int l1 = Integer.parseInt(sk.pop());
                if(s.equals("+")){
                    sk.push(Integer.toString(l1 + l2));
                }
                if(s.equals("-")){
                    sk.push(Integer.toString(l1 - l2));
                }
                if(s.equals("*")){
                    sk.push(Integer.toString(l1 * l2));
                }
                if(s.equals("/")){
                    sk.push(Integer.toString(l1 / l2));
                }
            }
            else sk.push(s);
        }
        return Integer.parseInt(sk.pop());
    }

    /**
     *
     * 给定一个经过编码的字符串，返回它解码后的字符串。

     编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

     你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

     此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。



     示例 1：

     输入：s = "3[a]2[bc]"
     输出："aaabcbc"
     示例 2：

     输入：s = "3[a2[c]]"
     输出："accaccacc"
     示例 3：

     输入：s = "2[abc]3[cd]ef"
     输出："abcabccdcdcdef"
     */
    public static String decodeString(String s) {
        Stack<String> stack = new Stack();
        for(Character c : s.toCharArray()){
            if(c != ']') stack.push(String.valueOf(c));
            else{
                String temp = new String();
                while(true){
                    String cur = stack.pop();
                    if(cur.equals("[") ) break;
                    temp = cur +  temp;
                }
                String num = new String();
                while(!stack.isEmpty() && Character.isDigit(stack.peek().toCharArray()[0])){
                    num = stack.pop() + num;
                }
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < Integer.parseInt(num); i++){
                    sb.append(temp);
                }
                stack.push(sb.toString());
            }
        }
        StringBuilder res = new StringBuilder();
        for(String ts : stack){
            res.append(ts);
        }

        return res.toString();
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        Queue<Integer> q = new LinkedList();
        Set<Integer> visited = new HashSet();
        q.offer(0);
        visited.add(0);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int t : rooms.get(cur) ){
                if(!visited.contains(t)){
                    q.offer(t);
                    visited.add(t);
                }
            }
        }

        return visited.size() == N;
    }

    public static void main(String[] args){
        //System.out.println(Solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        //System.out.println(Solution.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        //System.out.println((Solution.decodeString("3[a]2[bc]")));
        List<List<Integer>> s = new ArrayList<>(4);
        s.add(new ArrayList<>());
        s.add(new ArrayList<>());
        s.add(new ArrayList<>());
        s.add(new ArrayList<>());
       s.get(0).add(1);
        s.get(1).add(1);

        s.get(2).add(1);
        Integer[] t1 = new Integer[]{2,3};
        List l1 = Arrays.asList(t1);
        Integer[] r1 = new Integer[l1.size()];
        l1.toArray(r1);

        int[][] t2 = new int[][]{{2,3},{1,4}};
        List l2 = Arrays.asList(t2);
        int[][] r2 = (int[][])l2.toArray(new int[0][]);




        System.out.println(Solution.canVisitAllRooms(s));
    }
}
