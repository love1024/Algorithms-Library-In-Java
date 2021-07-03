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
        if(a == 0 && b == 0)
            return null;

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

    /**
     * Check if the equation has some positive solution i.e x > 0 && y > 0
     * @param a a of equation
     * @param b b of equation
     * @param c c of equation
     * @return whether the equation has a positive solution or not
     */
    public static boolean havePositiveSolution(int a, int b, int c) {
        // First find a single solution
        Solution s = findASolution(a,b,c);
        if(s == null)
            return false;

        // If both sign are already positive, then we already have the solution
        if(s.x > 0 && s.y > 0) return true;
        else if(s.x < 0 && s.y < 0) return false; // if both are negative, then we cannot make it positive
        else {
            // Now, we will shift the negative value of x or y to
            // just above or equal to 0 and check whether the other
            // value is still positive, if yes, we found the solution
            // otherwise, there is no positive solution
            int g = Euclidean.gcd(a, b);
            if(s.x < 0) {
                // Find the count required to make x +ve
                int cnt = -s.x*g % b > 0 ? (-s.x*g/b + 1) : -s.x*g/b;
                // Now, shift both x and y using this count
                shiftSolution(s, a/g, b/g, cnt);
            } else {
                int cnt = -1*(-s.y*g % a > 0 ? (-s.y*g/a + 1) : -s.y*g/a);
                shiftSolution(s, a/g, b/g, cnt);
            }
            // If both are +ve, then we have the solution, otherwise
            // we can't find it, as the other value will keep become negative
            if(s.x > 0 && s.y > 0) return true;
            else return false;
        }
    }

    /**
     * Shift solutions using given count
     * Note we are shifting using following formula which
     * is used to find multiple solutions after finding
     * a single one
     * x = x' + k * (b/g)
     * y = y' + k * (a/g)
     * @param s Initial solution
     * @param a a in the equation
     * @param b b in the equation
     * @param cnt Shift by this multiple
     */
    public static void shiftSolution(Solution s, int a, int b, int cnt) {
        s.x += cnt*b;
        s.y -= cnt*a;
    }

    public static Solution[] findSolutionsInRange(int a, int b, int c, int minx, int maxx, int miny, int maxy) {
        Solution s = findASolution(a, b, c);
        if(s == null)
            return null;
        int gcd = Euclidean.gcd(a , b);

        // converting ax+by = c to ax/g + by/g = c/g for easier calculatin in
        // shift operation
        a /= gcd;
        b /= gcd;

        int sign_a = a > 0 ? 1 : -1;
        int sign_b = b > 0 ? 1 : -1;
        int minX1Fixed;
        int maxX1Fixed;
        int minX2Fixed;
        int maxX2Fixed;


        // First, try to adjust solution's to just >= minx
        // Note: minx-sx could be negative means, x > min.x
        // so we are shifting down the value of s.x
        shiftSolution(s, a, b, (minx - s.x)/b);



        // If x went one below the minx, shift it up by one
        // if b is negative, then sign_b will it make is +ve again
        if(s.x < minx) shiftSolution(s, a, b, sign_b);

        // if x has reached more than maxx, then it means
        // it means b jump from one value to other is taking
        // x from less than minx to more than maxx, so no
        // solution between minx and maxx exist
        if(s.x > maxx) return null;

        // Store this min value, so that while doing operations
        // for maxx, this value will not be lost
        minX1Fixed = s.x;

        // Do the same as above for maxx
        shiftSolution(s, a, b, (maxx - s.x)/b);
        if(s.x > maxx) shiftSolution(s, a, b, -sign_b);
        maxX1Fixed = s.x;


        // Now do the same for y min and max values
        // Negative sign is required between in equation
        // we already have a negative
        // IMPORTANT: We are only finding the suitable value of x
        // after finding the y value in the range of miny and maxy
        shiftSolution(s, a, b, -(miny - s.y)/a);
        if(s.y < miny) shiftSolution(s, a, b, -sign_a);
        if(s.y > maxy) return null;
        minX2Fixed = s.x;
        shiftSolution(s, a, b, -(maxy- s.y)/a);
        if(s.y > maxy) shiftSolution(s, a, b, sign_a);
        maxX2Fixed = s.x;

        // If max2Fixed becomes more than than minX2Fixed then exchange
        // This can happen we are shifting for Y here not for X
        if(minX2Fixed > maxX2Fixed) {
            int temp = minX2Fixed;
            minX2Fixed = maxX2Fixed;
            maxX2Fixed = temp;
        }


        // Now find the overlap of the two X lines found
        // One which actually point to range of X, and other
        // one which point to corresponding Y range
        int minX = Math.max(minX1Fixed, minX2Fixed);
        int maxX = Math.min(maxX1Fixed, maxX2Fixed);

        // if there is no overlap between x and y range solutions
        // For example-> minX1Fixed-----maxX1Fixed  minX2Fixed---- maxX2Fixed
        if(minX > maxX) return null;

        // Now find total solutions between minX and maxX
        int totalSolutions = (maxX - minX) / b + 1;

        Solution[] sols = new Solution[totalSolutions];
        sols[0] = new Solution(minX, (c/gcd - a*minX) / b); // Finding y using ax/g + by/g = c/g
        int i = 1;
        while(sols[i-1].x != maxX) {
            int x = minX + i*b; // using formula x = x' + k*(b/g)
            int y = (c/gcd - a*x) / b;
            sols[i] = new Solution(x, y);
            i++;
        }

        return sols;
    }

    public static void main(String[] args) {
        int a = 16;
        int b = 58;
        int c = 410;
        Solution s = findASolution(a,b,c);
        if(s != null) {
            System.out.println(a+"*"+s.x+" + " + b+"*"+s.y + " = " + c);

            Solution[] sols = findMultipleSolutions(a,b,c, 10);
            for(int i=0; i < sols.length; i++) {
                System.out.println("Solution " + (i+1) + " : " + sols[i].x + " " + sols[i].y);
            }

            int minx = 0;
            int maxx = 50;
            int miny = 0;
            int maxy = 50;
            System.out.println("Finding solutions in a range: X:[" + minx+", "+maxx+"] Y:["+miny+", "+maxy+"]");
            Solution[] solsInRange = findSolutionsInRange(a,b,c, minx, maxx, miny, maxy);
            if(solsInRange == null) {
                System.out.println("No solution exist.");
            } else {
                for(int i=0; i < solsInRange.length; i++) {
                    System.out.println("Solution " + (i+1) + " : " + solsInRange[i].x + " " + solsInRange[i].y);
                }
            }
        } else {
            System.out.println("No solution exist.");
        }
    }
}
