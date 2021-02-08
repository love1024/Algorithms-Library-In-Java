package UnionFind;

/**
 * Algorithm: Weighted Union Find + Path Compression
 * Author   : Lovepreet Singh
 */
public class UnionFind {
    private int[] parent;
    private int[] sizes;

    public UnionFind(int N) {
        // Set parent of each element to itself
        // (N array accesses)
        parent = new int[N];
        sizes = new int[N];
        for (int i=0; i<N; i++) {
            parent[i] = i;
            sizes[i] = 1;
        }
    }

    int root(int p) {
        // Check parent pointers until reach root
        // (Depth of i array accesses)
        while (parent[p] != p) {

            //PATH COMPRESSION
            //No need to traverse path all time
            //Just make it point to grandparent instead of parent
            //Halving the length to traverse, as we only care about root
            parent[p] = parent[parent[p]];

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

        // If they already under same parent, no need to do anything
        if(p_root == q_root) return;

        // If
        if(sizes[p_root] < sizes[q_root]) {
            this.parent[p_root] = q_root;
            sizes[q_root] += sizes[p_root];
        } else {
            this.parent[q_root] = p_root;
            sizes[p_root] += sizes[q_root];
        }
    }
}
