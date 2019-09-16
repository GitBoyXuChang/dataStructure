package com.xuchang.ds.st;

import com.xuchang.ds.graph.FileUtils;

import java.util.NoSuchElementException;
import java.util.Stack;


public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean hasNegativeCycle;

    public BellmanFordSP(EdgeWeightedDirectedGraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        //O(VE)
        for(int i=1; i<G.V(); i++) { //V
            for(int j=0; j<G.V(); j++) {
                relax(G, j);
            } // E
        }

        hasNegativeCycle = detectNegativeCycle(G);

        System.out.println("The graph has negative cycle: " + this.hasNegativeCycle);
    }

    public double distTo(int v) {
        if(hasNegativeCycle) {
            throw new RuntimeException("graph has negativecycle");
        }
        return distTo[v];
    }

    private void relax(EdgeWeightedDirectedGraph G, int v) {
        for(DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    private boolean detectNegativeCycle(EdgeWeightedDirectedGraph G) {
        for(int v=0; v<G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight()) {
                    return true;

                }
            }
        }

        return false;
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
            BellmanFordSP sp = new BellmanFordSP(graph, s);

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
