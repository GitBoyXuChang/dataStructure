package com.xuchang.ds.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class AdjListGraph implements  Graph{
    private final int V;
    private int E;
    private List<Integer>[] adj;

    public AdjListGraph(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("节点个数为能小于0");
        }
        this.V = v;
        this.E = 0;
        this.adj = new LinkedList[V];

        for(int i=0; i<V; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public void addEdge(int v, int w) {
        //if exist??
        adj[v].add(w);
        adj[w].add(v);

        E++;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for(int v=0; v<V; v++) {
            s.append(v + ": ");
            for(int w: adj(v)){
                s.append(w + " ");
            }

            s.append("\n");
        }

        return  s.toString();
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
