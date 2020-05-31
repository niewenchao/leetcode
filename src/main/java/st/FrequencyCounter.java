package st;

import com.sun.tools.corba.se.idl.constExpr.Or;
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
           ST<String, Integer> st = new LinearProbingHashST<String, Integer>();
            Scanner scan = new Scanner(new FileInputStream(args[1]));
            long startTime=System.currentTimeMillis();
            while (scan.hasNext()) {
                String word = scan.next();
                if (word.length() < minlen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else                    st.put(word, st.get(word) + 1);
            }
            scan.close();
            System.out.println("numkey:" + st.size());
           // System.out.println("maxkey:" + st.max());
           // System.out.println("minkey:" + st.min());

            String max = "";
            st.put(max, 0);
            for (String word : st.keys()){
              //  System.out.println("key:value==" + word+":"+st.get(word) );
                if (st.get(word) > st.get(max))
                    max = word;
            }
            long endTime=System.currentTimeMillis();

            System.out.println(max + " " + st.get(max));
            System.out.println("time:" + " " + (endTime - startTime));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

/***************************************
 % java FrequancyCounter 1 < tinyTale.txt
 it 10
 ***************************************/


