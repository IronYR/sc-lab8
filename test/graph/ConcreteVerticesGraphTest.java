/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */


    // cover vertex number 0
    @Test
    public void testToStringEmptyGraph() {
    	Graph<String> graph = emptyInstance();
    	assertEquals("expected graph", "\n", graph.toString());
    }
    
  
    /**
     * cover target in targets and weight 0
     * 	target not same
     * 	targets 1
     * 	sources 0
     */
    @Test
    public void testToRemoveEdge() {
    	Vertex<String> vertex1 = new Vertex<>("1");
    	Vertex<String> vertex2 = new Vertex<>("2");
    	assertEquals("expected edge not exist", 0, vertex1.to(vertex2, 1));
    	
    	assertEquals("expected targets number", 0, vertex1.sources().size());
    	assertEquals("expected targets", Map.of("2", 1), vertex1.targets());
    	assertEquals("expected source", Map.of("1", 1), vertex2.sources());
    	
    	assertEquals("expected edge exist", 1, vertex1.to(vertex2, 0));
    	assertEquals("expected targets number", 0, vertex1.sources().size());
    	assertEquals("expected targets number", 0, vertex1.targets().size());
    	assertEquals("expected targets number", 0, vertex2.sources().size());
    }
    
    
    
    /**
     * cover targets 0
     * 	sources 0
     */
    @Test
    public void testToString() {
    	Vertex<String> vertex = new Vertex<>("1");
    	assertEquals("expected blank line", "1", vertex.toString());
    }
}
