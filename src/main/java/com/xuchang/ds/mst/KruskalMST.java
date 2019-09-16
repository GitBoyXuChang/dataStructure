package com.xuchang.ds.mst;

import com.xuchang.ds.graph.FileUtils;
import com.xuchang.ds.heap.MinHeap;
import com.xuchang.ds.uf.WeigtedQuickUnionWithPathCompressionUFV1;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class KruskalMST implements MST {
    private double weight;

    private Queue<Edge> mst;
    private boolean[] visited;


    public KruskalMST(EdgeWeightedGraph G) {
        mst = new LinkedList<Edge>();
        visited = new boolean[G.V()];

        MinHeap<Edge> pq = new MinHeap<>();
        for(Edge e: G.edges()) {
            pq.add(e);
        }
        WeigtedQuickUnionWithPathCompressionUFV1 uf = new WeigtedQuickUnionWithPathCompressionUFV1(G.V());

        //E => ElogE
        while(!pq.isEmpty() && mst.size() < G.V() - 1) {
            //logE
            Edge e = pq.extractMin();
            int v = e.either();
            int w = e.other(v);

            if(uf.connected(v, w))  {
                continue;
            }

            uf.union(v, w);

            mst.add(e);
            weight += e.weight();

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

            KruskalMST mst = new KruskalMST(graph);

            for(Edge e: mst.edges()) {
                System.out.println(e);
            }

            System.out.printf("%.5f\n", mst.weight());

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }

    }
}
