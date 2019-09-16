package com.xuchang.ds.st;
 

public interface EdgeWeightedDirectedGraph {

	public int V();

	public int E();

	public void addEdge(DirectedEdge e);

	public Iterable<DirectedEdge> adj(int v);

	public Iterable<DirectedEdge> edges();

}
