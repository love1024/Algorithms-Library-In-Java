package Math;

/**
 * Reference: https://cp-algorithms.com/algebra/euclid-algorithm.html
 * Extended Euclidean: https://cp-algorithms.com/algebra/extended-euclid-algorithm.html
 */
public class Euclidean {
    // Return Node for Extended Euclidean
    public static class Node {
        int gcd;
        int x;
        int y;
        Node(int gcd, int x, int y) {
            this.x = x;
            this.y = y;
            this.gcd = gcd;
        }
    }

    /**
     * Calculate greatest common divisor
     * using Euclidean algorithm
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a%b) : a;
    }

    /**
     * Extended Euclidean to find values of x and y in
     * ax + by = gcd(a,b)
     * besides gcd
     * @param a
     * @param b
     * @return
     */
    public static Node extended(int a, int b) {
        if(b == 0) {
            return new Node(a, 1, 0);
        }

        Node ret = extended(b, a%b);
        int x = ret.y;
        int y = ret.x - ret.y * (int)Math.floor(a / b);
        return new Node(ret.gcd, x, y);
    }

    public static void main(String[] args) {
        System.out.println(gcd(12,15));
        System.out.println(gcd(3,5));
        System.out.println(gcd(10,15));
        System.out.println("---------------");

        Node ret = extended(55, 80);
        System.out.println(ret.gcd + " " + ret.x + " " + ret.y);
    }
}
