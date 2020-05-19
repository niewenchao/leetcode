import java.util.Arrays;
import java.io.*;






public class BinarySearch {
    public static int rank(int key, int[] a){
        int low = 0,high = a.length -1;
        while(low <= high){
            int mid = (high + low)/2;
            if(key < a[mid]) high = mid - 1;
            if(key > a[mid]) low = mid + 1;
            if(key == a[mid]) return mid;
        }
        return -1;
    }

    public static void main(String[] args){
        try{
            int[] whitelist = {1,2,3,4};
            System.out.println(BinarySearch.rank(3,whitelist));

        }catch (Exception e){

        }
    }
}
