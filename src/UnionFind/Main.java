package UnionFind;

public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(4,3);
        uf.union(3,8);
        uf.union(6, 5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(7,3);

        System.out.println(uf.connected(1,3));
        System.out.println(uf.connected(1,2));
        System.out.println(uf.connected(1,4));
        System.out.println(uf.connected(1,5));
        System.out.println(uf.connected(1,6));
        System.out.println(uf.connected(1,7));
        System.out.println(uf.connected(1,8));
        System.out.println(uf.connected(1,9));
        System.out.println(uf.connected(1,0));


        //Check maximul element
        UnionFind uf1 = new UnionFind(10);

        uf1.union(1,2);
        uf1.union(6,9);
        uf1.union(2,6);

        System.out.println(uf1.findMax(1));
    }
}
