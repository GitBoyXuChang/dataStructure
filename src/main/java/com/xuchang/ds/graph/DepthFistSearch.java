package com.xuchang.ds.graph;


public class DepthFistSearch {
    private boolean[] visited;
    private int count;

    public DepthFistSearch(Graph G, int s) {
        visited = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph g, int s) {
        count++;
        visited[s] = true;
        System.out.print(s + " ");

        for(int w: g.adj(s)) {
            if(!visited[w]) {
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public  void dfs(Graph g) {
        for(int v=0; v<g.V(); v++) {
            visited[v] = false;
        }
        for(int v=0; v<g.V(); v++) {
            if(!visited[v]) {
                dfs(g, v);
            }
        }
    }

    public  static void main(String[] args) {
        FileUtils in  = null;
        try{
            in = new FileUtils("./tinyG.txt");

            int V = in.readInt();
            Graph  graph = new AdjListGraph(V);

            int E = in.readInt();
            if(E < 0) {
                throw new IllegalArgumentException("边数量不能小于0");
            }

            for(int i=0; i<E; i++) {
                int v = in.readInt();
                int w = in.readInt();

                graph.addEdge(v, w);
            }

            System.out.println("Graph is: " + graph);

            DepthFistSearch s = new DepthFistSearch(graph, 2);
            System.out.println("\n===============================\n");

            s.dfs(graph);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
