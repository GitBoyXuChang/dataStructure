package com.xuchang.ds.graph;

import java.util.Stack;


public class ComponentCount {
    private boolean[] visited;
    private int count;
    private int[] id;
    private int[]  size;

    public ComponentCount(Graph G) {
        visited = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];

        for(int v=0; v < G.V(); v++){
            if(!visited[v]) {
                dfs(G, v);
                count++;
            }
        }


    }

    private void dfs(Graph g, int s) {
        visited[s] = true;
        id[s] = count;

        size[count]++;

        for(int w: g.adj(s)) {
            if(!visited[w]) {
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }



    public int id(int v) {
        return id[v];
    }

    public int size(int v) {
        return size[id[v]];
    }

    public  boolean connected(int v, int w) {
        return id[v] == id[w];
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

            int s = 2;
            ComponentCount df = new ComponentCount(graph);

            int cc = df.count();
            System.out.println("The graph has " + cc + " components");
            System.out.println("\n===============================\n");


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
