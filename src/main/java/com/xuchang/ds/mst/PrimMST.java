package com.xuchang.ds.mst;

import com.xuchang.ds.graph.FileUtils;
import com.xuchang.ds.heap.MinHeap;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class PrimMST implements MST {
    private double weight;

    private Queue<Edge> mst;
    private boolean[] visited;

    private MinHeap<Edge> pq;

    public PrimMST(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        pq = new MinHeap<>();
        visited = new boolean[G.V()];

        for(int v=0; v<G.V(); v++) {
            if(!visited[v]) {
                prim(G, v);
            }
        }

    }



    //ElogE  vs ElogV (E >= V - 1)
    private void prim(EdgeWeightedGraph G, int s) {
        addEdgeToHeap(G, s);

        //E
        while(!pq.isEmpty()){
            //LogE => logV
            Edge e = pq.extractMin();
            int v = e.either();
            int w = e.other(v);
            if(visited[v] && visited[w]) {
                continue;
            }

            mst.add(e);
            weight += e.weight();

            if(!visited[v]) {
                addEdgeToHeap(G, v);
            }

            if(!visited[w]) {
                addEdgeToHeap(G, w);
            }
        }
    }

    private void addEdgeToHeap(EdgeWeightedGraph G, int v) {
        if(visited[v]) {
            return;
        }

        visited[v] = true;

        for(Edge e: G.adj(v)) {
            if(!visited[e.other(v)]) {
                pq.add(e);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return this.weight;
    }

    public static void main(String[] args) {

        FileUtils in = null;
        try {
            in = new FileUtils("./tinyEWG.txt");

            int V = in.readInt();
            EdgeWeightedAdjListGraph graph = new EdgeWeightedAdjListGraph(V);

            int E = in.readInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                Edge e = new Edge(v, w, weight);
                graph.addEdge(e);

            }

            System.out.println("Graph is : " + graph);

            PrimMST mst = new PrimMST(graph);

            for(Edge e: mst.edges()) {
                System.out.println(e);
            }

            System.out.printf("%.5f\n", mst.weight());

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }

    }
}
