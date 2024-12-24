package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths {
    private int source;
    private boolean[] visited;
    private int[] edgeTo;

    public BreadthFirstPaths(Graph g, int s) {
        this.source = s;
        this.visited = new boolean[g.size()];
        this.edgeTo = new int[g.size()];

        bfs(g, s);
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

    private void bfs(Graph g, int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        this.visited[v] = true;

        while(!q.isEmpty()) {
            int curV = q.poll();
            for(int adj: g.adj(curV)) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    edgeTo[adj] = curV;
                    q.add(adj);
                }
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
