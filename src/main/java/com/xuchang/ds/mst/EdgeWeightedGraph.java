package com.xuchang.ds.mst;
 

public interface EdgeWeightedGraph {

	public int V();

	public int E();

	public void addEdge(Edge e);

	public Iterable<Edge> adj(int v);

	public Iterable<Edge> edges();

}
