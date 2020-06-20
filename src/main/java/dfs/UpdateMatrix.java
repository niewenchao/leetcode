package dfs;

public class UpdateMatrix {
    private boolean[][] visited;
    public int[][] updateMatrix(int[][] matrix) {
        visited = new boolean[matrix.length][];
        for(int i = 0; i < matrix.length; i++){
            visited[i] = new boolean[matrix[i].length];
        }
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != 0 && visited[i][j] == false)
                    dfs(matrix, i, j);
            }
        return matrix;
    }
    private int dfs(int[][] matrix, int i , int j){
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) return Integer.MAX_VALUE;
        if(matrix[i][j] == 0) return 0;
        if(visited[i][j]) return matrix[i][j];
        int a = dfs(matrix, i + 1, j);
        int b = dfs(matrix, i - 1, j);
        int c = dfs(matrix, i, j + 1);
        int d = dfs(matrix, i, j - 1);
        matrix[i][j] = Math.min(Math.min(a,b),Math.min(c,d)) + 1;
        return matrix[i][j];
    }

    public static void main(String[] args){
        System.out.println(new UpdateMatrix().updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}));
    }
}
