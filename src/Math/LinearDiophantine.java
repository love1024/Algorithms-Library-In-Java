package Math;

/**
 * Reference: https://cp-algorithms.com/algebra/linear-diophantine-equation.html
 */
public class LinearDiophantine {
    // Solution Node
    public static class Solution {
        int x;
        int y;
        Solution(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Find the solution of the equation: ax + by = c
     * which is Diaphantine Linear Equation
     * using extended Euclidean GCD function
     * @param a a of equation
     * @param b b of equation
     * @param c c of equation
     * @return The found solution or null if none exist
     */
    public static Solution findASolution(int a, int b, int c) {
        Euclidean.Node ret = Euclidean.extended(a, b);

        // If c is not divisible by gcd of a and b
        // Then no solution exist for such equation
        if(c%ret.gcd != 0) {
            return null;
        }

        int x = ret.x * (c / ret.gcd);
        int y = ret.y * (c / ret.gcd);
        if(a < 0) x = -1*x;
        if(b < 0) y = -1*y;
        return new Solution(x, y);
    }

    /**
     *
     * @param a a of equation
     * @param b b of equation
     * @param c c of equation
     * @param n number of solutions to return
     * @return Solution array or null if none exist
     */
    public static Solution[] findMultipleSolutions(int a, int b, int c, int n) {
        Solution s = findASolution(a,b,c);
        if(s == null)
            return null;

        int gcd = Euclidean.gcd(a , b);
        Solution[] sols = new Solution[n];
        sols[0] = s;

        for(int i=1 ;i < n; i++) {
            int nx = sols[i-1].x + i * (b / gcd);
            int ny = sols[i-1].y - i * (a / gcd);
            sols[i] = new Solution(nx, ny);
        }

        return sols;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 5;
        int c = 17;
        Solution s = findASolution(a,b,c);
        if(s != null) {
            System.out.println(a+"*"+s.x+" + " + b+"*"+s.y + " = " + c);
        } else {
            System.out.println("No solution exist.");
        }

        Solution[] sols = findMultipleSolutions(a,b,c, 4);
        for(int i=0; i < sols.length; i++) {
            System.out.println("Solution " + (i+1) + " : " + sols[i].x + " " + sols[i].y);
        }
    }
}
