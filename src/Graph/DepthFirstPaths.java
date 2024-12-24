package Graph;

import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] visited;
    private int[] edgeTo;
    private int source;

    public DepthFirstPaths(Graph g, int s) {
        this.source = s;
        this.visited = new boolean[g.size()];
        this.edgeTo = new int[g.size()];

        dfs(g, s);
    }

    public boolean hasPathTo(int v) {
        return this.visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        while(v != source) {
            path.add(v);
            v = edgeTo[v];
        }
        path.push(source);

        return path;
    }

    private void dfs(Graph g, int v) {
        visited[v] = true;
        for(int w: g.adj(v)) {
            if(!visited[w]) {
                dfs(g, w);  
                edgeTo[w] = v;
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        
        DepthFirstPaths dfp = new DepthFirstPaths(g, 0);
        System.out.println(dfp.hasPathTo(2));
        
        Iterable<Integer> path = dfp.pathTo(4);
        for(int p: path) {
            if(p == 0) System.out.print(0);
            else System.out.print(p + "<-");
        }
    }
}
