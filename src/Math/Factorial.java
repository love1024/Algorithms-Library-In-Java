package Math;

/**
 * Factorial operations
 */
public class Factorial {

    /**
     * Find factorial of n numbers modulus m
     * @param n numbers till factorial to found
     * @param m Modulus
     * @return factorials of n number modulus m
     * @Complexity O(n)
     */
    public static long[] fact(int n, int m) {
        long fact[] = new long[n+1];
        fact[0] = fact[1] = 1;
        for(int i = 2; i <= n; i++) {
            fact[i] = (i * fact[i-1]) % m;
        }

        return fact;
    }

    /**
     * Find inverse of factorials of n number modulus m
     * @param n Numbers till factorials inverse to found
     * @param m Modulus
     * @return Inverse of factorials of n numbers modulus m
     * @Complexity: O(n)
     */
    public static long[] factInv(int n, int m) {
        // First find inverse of n numbers
        long inv[] = Modulus.inverseAll(n, m);
        long invFact[] = new long[n+1];

        // Inv(n!) = Inv(n) * Inv((n - 1)!)
        inv[0] = inv[1] = 1;
        for(int i = 2; i <= n; i++) {
            invFact[i] = (inv[i] * invFact[i-1]) % m;
        }

        return invFact;
    }
}
