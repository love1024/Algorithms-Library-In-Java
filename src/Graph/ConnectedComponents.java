package Graph;

public class ConnectedComponents {
    private boolean[] visited;
    private int[] id;
    private int count;

    public ConnectedComponents(Graph g) {
        visited = new boolean[g.size()];
        id = new int[g.size()];

        findCC(g);
    }

    public int count() {
        return this.count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean isConnected(int v, int w) {
        return id[v] == id[w];
    }

    private void findCC(Graph g) {
        for(int i = 0; i < g.size(); i++) {
            if(!visited[i]) {
                dfs(g, i);
                count++;
            } 
        }
    }

    private void dfs(Graph g, int v) {
        visited[v] = true;
        id[v] = count;

        for(int adj: g.adj(v)) {
            if(!visited[adj]) {
                dfs(g, adj);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);

        g.addEdge(5, 6);
        g.addEdge(5, 7);
        
        
        ConnectedComponents cc = new ConnectedComponents(g);
        System.out.println("Total Components: " + cc.count());
        System.out.println("Is 0 connect to 4: " + cc.isConnected(0, 4));
        System.out.println("Is 1 connect to 6: " + cc.isConnected(1, 6));
    }
}
