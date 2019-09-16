package com.xuchang.ds.mst;

import java.util.LinkedList;
import java.util.Queue;

public interface MST {
 
 
 
    public Iterable<Edge> edges() ;

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight()  ;

 
    
    

}
