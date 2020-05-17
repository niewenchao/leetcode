public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] temp = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    temp[0] = i;
                    temp[1] = j;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args){
        System.out.println("hello world");
        Solution s = new Solution();
        int [] arr = {1,2,3,4,5};
        int[] temp = s.twoSum(arr,8);
        System.out.println(temp[0] + "c:" + temp[1]);

    }

}
