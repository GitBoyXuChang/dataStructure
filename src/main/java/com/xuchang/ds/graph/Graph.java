package com.xuchang.ds.graph;


public interface Graph {

	public int V();

	public int E();

	public Iterable<Integer> adj(int v);

	public void addEdge(int v, int w);
}
