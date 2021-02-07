package UnionFind;

public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(1,2);
        System.out.println(uf.connected(1,3));
    }
}
