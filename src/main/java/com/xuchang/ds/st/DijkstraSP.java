package com.xuchang.ds.st;


import com.xuchang.ds.graph.FileUtils;
import com.xuchang.ds.mst.IndexMinPQ;

import java.util.NoSuchElementException;
import java.util.Stack;


public class DijkstraSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDirectedGraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException("Edge: " + e + " has negative weight ...");
            }
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;

        //用Heap， 数组??实现一下
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);

        //O(Vlogv) ? O(ElgV)
        while (!pq.isEmpty()) {
            int v = pq.delMin(); //O(1)
            for (DirectedEdge e : G.adj(v)) {
                relax(e); //O(lgv)
            }
        }
    }



    //O(logv)
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;

            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    public boolean hasPathTo(int v ) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v) {
         if(!hasPathTo(v)){
             return null;
         }

        Stack<DirectedEdge> path = new Stack<>();
         for(DirectedEdge e=edgeTo[v]; e!=null; e=edgeTo[e.from()]){
             path.push(e);
         }

         return path;
    }

    public static void main(String[] args) {
        FileUtils in = null;
        try {
            in = new FileUtils("./tinyEWG.txt");

            int V = in.readInt();
            EdgeWeightedAdListDigraph graph = new EdgeWeightedAdListDigraph(V);

            int E = in.readInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, weight);
                graph.addEdge(e);

            }

            System.out.println("Graph is : " + graph);

            int s = 0;
            DijkstraSP sp = new DijkstraSP(graph, s);

            for(int i=0; i<graph.V(); i++) {
                if(sp.hasPathTo(i)) {
                    System.out.printf("%d to %d (%.2f)  ",s , i, sp.distTo[i]);

                    for(DirectedEdge e: sp.pathTo(i)) {
                        System.out.print(e + " ");
                    }

                    System.out.println();
                }else {
                    System.out.printf("%d to %d has no path\n", s, i);
                }
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }
}
