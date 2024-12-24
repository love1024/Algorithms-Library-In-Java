package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int V;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.adj = (List<Integer>[]) new List[V];

        for(int i = 0; i < V; i++) {
            this.adj[i] = new ArrayList<>();
        }
    }

    public int size() {
        return this.V;
    }

    public void addEdge(int w, int v) {
        this.adj[w].add(v);
        this.adj[v].add(w);
    }

    public List<Integer> adj(int v) {
        return this.adj[v];
    }
}
