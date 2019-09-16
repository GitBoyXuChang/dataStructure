package com.xuchang.ds.graph;


import java.util.LinkedList;


public class BreadthFistSearch {
    private boolean[] visited;
    private int count;

    public BreadthFistSearch(Graph G, int s) {
        visited = new boolean[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph g, int s) {

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        visited[s] = true;

        while(!queue.isEmpty()) {
            s = queue.poll();
            count++;
            System.out.print(s + " ");

            Iterable<Integer> adj = g.adj(s);
            for(int i: adj) {
                if(!visited[i]) {
                    queue.add(i);
                    visited[i] =true;
                }
            }
        }

    }

    public int count() {
        return count;
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

            BreadthFistSearch s = new BreadthFistSearch(graph, 2);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
