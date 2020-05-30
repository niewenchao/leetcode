package st;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FrequencyCounter
{
    public static void main(String[] args)
    {
        try {
            int minlen = Integer.parseInt(args[0]);
            OrderedST<String, Integer> st = new RedBlackBST <String, Integer>();
            Scanner scan = new Scanner(new FileInputStream(args[1]));
            while (scan.hasNext()) {
                String word = scan.next();
                if (word.length() < minlen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else                    st.put(word, st.get(word) + 1);
                System.out.println("输入的数据为：" + word);
            }
            scan.close();
            System.out.println("输入的数据keys：" + st.keys().toString());
            String max = "";
            st.put(max, 0);
            for (String word : st.keys())
                if (st.get(word) > st.get(max))
                    max = word;
            System.out.println(max + " " + st.get(max));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

/***************************************
 % java FrequancyCounter 1 < tinyTale.txt
 it 10
 ***************************************/


