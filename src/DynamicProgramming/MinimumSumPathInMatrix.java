package DynamicProgramming;

/**
 * Given a matrix with n*n numbers, find
 * the path from top left (0,0) to the bottom
 * right (n-1,n-1) such that sum will be minimum
 */
public class MinimumSumPathInMatrix {

    /**
     * Find the minimum sum taking some path from
     * start (0,0) to end (n-1, n-1) where we can
     * only move right or down
     * @param matrix the number matrix
     * @param mem memory to store minimum sum each cell can have
     * @param n Total number of rows and columns
     */
    public static void findMinimumSum(int matrix[][], int mem[][], int n) {
        // First cell we always be same, as no other path
        mem[0][0] = matrix[0][0];

        // In first row, we can only come from the left
        for (int i = 1; i < n; i++) {
            mem[0][i] = mem[0][i-1] + matrix[0][i];
        }

        // In first column, we can only come from the top
        for (int i = 1; i < n; i++) {
            mem[i][0] = mem[i-1][0] + matrix[i][0];
        }

        // Traverse rest of the cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // Take the path which is coming from the minimum, either from left
                // or from the top
                mem[i][j] = Math.min(mem[i-1][j], mem[i][j-1]) + matrix[i][j];
            }
        }
    }

    /**
     * Print minimum path which leads to the end
     * 'D' for down, 'R' for right
     * @param matrix the number matrix
     * @param mem memory to store minimum sum each cell can have
     * @param n Total number of rows and columns
     */
    public static void printMinimumPath(int matrix[][], int mem[][], int n) {
        // start from the last cell
        int i = n-1, j = n-1;

        StringBuilder str = new StringBuilder();

        // While we haven't reached the top left corner
        // from where we started
        while(i > 0 || j > 0) {

            // if this is first row, we can only come
            // from the left
            if(i == 0) {
                str = str.append("R");
                j--;
            } else if(j == 0) {
                // If we are at first column, we can only
                // from from the top
                str = str.append("D");
                i--;
            } else if(mem[i][j] == mem[i-1][j] + matrix[i][j]) {
                // Else the sum of minimum sum at top cell + this cell value
                // makes the minimum cell at current cell. Means, we have come
                // from the top
                str = str.append("D");
                i--;
            } else {
                // Lastly, we can only come from left, we no condition is
                // accepted at top
                str = str.append("R");
                j--;
            }
        }

        // Reverse the string to print
        // path from the start
        str = str.reverse();

        System.out.println(str);
    }


    public static void main(String[] args) {
        int n = 3;
        int matrix[][] = {
                {1, 2, 3},
                {1, 1, 1},
                {7, 8, 1}
        };
        int mem[][] = new int[n][n];

        findMinimumSum(matrix, mem, n);
        System.out.println("MINIMUM SUM: " + mem[n-1][n-1]);
        printMinimumPath(matrix, mem, n);
    }
}
