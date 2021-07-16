package Math;

/**
 * Modulus operations
 */
public class Modulus {

    /**
     * Find inverse of a single number n modulus m
     * Using the extended Euclidean algorithm for co prime a and m: ax + my = 1;
     * SOURCE: https://cp-algorithms.com/algebra/module-inverse.html
     * @param n The number whose inverse to find
     * @param m Modulus
     * @return Inverse of number n
     * @Complexity: log(m)
     */
    public static long inverse(int n, int m) {
        Euclidean.Node sol = Euclidean.extended(n, m);

        // Not possible if n and m are not coprime
        if(sol.gcd != 1) {
            return -1;
        }
        // x should be the inverse here, but we need to make
        // sure its positive first by adding modulus
        int x = (sol.x % m + m) % m;

        return x;
    }

    /**
     * Find the inverse of all numbers till n modolus m
     * Using the recursive equation: inv(i) = inv(m%i) * inv(m - m/i) mod m
     * SOURCE: https://cp-algorithms.com/algebra/module-inverse.html#mod-inv-all-num
     * @param n Number till the inverse to found
     * @param m Modulus
     * @return Inverse of all numbers till n
     * @Complexity: O(m)
     */
    public static long[] inverseAll(int n, int m) {
        long inv[] = new long[n+1];
        inv[0] = inv[1] = 1;
        for(int i = 2; i <= n; i++) {
            inv[i] = inv[m%i] * (m - m / i) % m;
        }

        return  inv;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 17;

        long inv[] = inverseAll(n, m);
        for(int i=1;i<=n;i++)
            System.out.print(inv[i] + " ");
        System.out.println();

        long invSingle = inverse(10, m);
        System.out.println("Inverse of 10: " + invSingle);
    }
}
