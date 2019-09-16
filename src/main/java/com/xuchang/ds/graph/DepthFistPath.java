package com.xuchang.ds.graph;

import java.util.Stack;


public class DepthFistPath {
    private boolean[] visited;
    private int count;
    private int[] edgeTo; //edgeTo[v]: s->v路径中v的上一个节点
    private int s;

    public DepthFistPath(Graph G, int s) {
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph g, int s) {
        count++;
        visited[s] = true;

        for(int w: g.adj(s)) {
            if(!visited[w]) {
                edgeTo[w] = s;
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> printPath(int v) {
        if(!hasPath(v)) {return null;}

        Stack<Integer> stack = new Stack();
        for(int i=v; i!=s; i=edgeTo[i]) {
            stack.push(i);
        }

        stack.push(s);

        return  stack;

    }

    public boolean hasPath(int v) {
        return visited[v];
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

            int s = 2;
            DepthFistPath df = new DepthFistPath(graph, 2);
            System.out.println("\n===============================\n");

            for(int v=0; v<graph.V(); v++){
                if(df.hasPath(v)) {
                    System.out.printf("%d to %d: ", s, v);
                    for(int x: df.printPath(v)) {
                        if(x == s) {
                            System.out.print(x);
                        } else {
                            System.out.print(x + "->");
                        }
                    }
                    System.out.println();
                }else {
                    System.out.printf("%d to %d: not connected\n", s, v);
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
