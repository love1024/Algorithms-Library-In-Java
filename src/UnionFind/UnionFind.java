package UnionFind;

/**
 * Union Find Data Structure For Dynamic Component
 * Connections
 */
public class UnionFind {
    private int[] parent;

    public UnionFind(int N) {
        // Set parent of each element to itself
        // (N array accesses)
        parent = new int[N];
        for (int i=0; i<N; i++) {
            parent[i] = i;
        }
    }

    int root(int p) {
        // Check parent pointers until reach root
        // (Depth of i array accesses)
        while (parent[p] != p) {
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        // Check if p and q have the same root
        // (Depth of p and q array accesses)
        return this.root(p) == this.root(q);
    }

    public void union(int p, int q) {
        // Change root of p to point to q
        // (Depth of p and q array accesses)
        int p_root = this.root(p);
        int q_root = this.root(q);

        this.parent[p_root] = q_root;
    }
}
