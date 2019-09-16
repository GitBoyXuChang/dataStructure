package com.xuchang.ds.graph;

import java.util.Iterator;


public class AdjMatrixGraph  implements  Graph{
    private final int V;
    private int E;
    private boolean[][] adj;

    public AdjMatrixGraph(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("节点个数为能小于0");
        }
        this.V = v;
        this.E = 0;
        this.adj = new boolean[V][V];
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
        return new AdjIterator(v);
    }

    @Override
    public void addEdge(int v, int w) {
        if(!adj[v][w]) {
            E++;
        }

        adj[v][w] = true;
        adj[w][v] = true;

    }

    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        private int v;
        private int w = 0;

        AdjIterator(int v) {
            this.v = v;
        }

        @Override
        public Iterator<Integer> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            while(w < V) {
                if(adj[v][w]) {
                    return true;
                }
                w++;
            }

            return false;
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                throw new RuntimeException("no more edge...");
            }
            return w++;
        }

        @Override
        public void remove() {

        }
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
            Graph  graph = new AdjMatrixGraph(V);

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
