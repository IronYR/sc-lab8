/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
   
    /*
     * cover not included in graph,
     * 	vertices number 0
     */
    @Test
    public void testAddToEmptyGraph() {
    	Graph<String> graph = emptyInstance();
    	assertTrue("expected add succeed", graph.add("1"));
    	assertEquals("expected graph contain one vertices", Set.of("1"), graph.vertices());
    }
    
    /*
     * cover included in graph,
     * 	produced by set(),
     * 	vertices number 1
     */
    @Test
    public void testAddDuplicateVertex() {
    	Graph<String> graph = emptyInstance();
    	graph.set("1", "1", 1);
    	assertFalse("expected add failed", graph.add("1"));
    	assertEquals("expected graph vertices", Set.of("1"), graph.vertices());
    }
    
    /*
     * cover not included in graph,
     * 	produced by set(),
     * 	vertices number >1
     */
    @Test
    public void testAddToMultipleVerticesGraph() {
    	Graph<String> graph = emptyInstance();
    	graph.set("1", "2", 1);
    	assertTrue("expected add succeed", graph.add("3"));
    	assertEquals("expected graph vertices", Set.of("1", "2", "3"), graph.vertices());
    }
    
    
    
    /*
     * cover source not included and target include,
     * 	edge not included, weight >0,
     * 	produced by add(),
     * 	source!=target
     * 	vertices number 1
     */
    @Test
    public void testSetAddSourceVertexAndEdge() {
    	Graph<String> graph = emptyInstance();
    	graph.add("2");
    	assertEquals("expected no such edge", 0, graph.set("1", "2", 1));
    	assertEquals("expected graph vertices", Set.of("1", "2"), graph.vertices());
    	assertEquals("expected one edge from source", Map.of("2", 1), graph.targets("1"));
    	assertEquals("expected one edge to target", Map.of("1", 1), graph.sources("2"));
    }
    
    /*
     * cover produced by add(), set()
     * 	vertices number 1, >1
     */
    @Test
    public void testVertices() {
    	Graph<String> graph = emptyInstance();
    	graph.add("1");
    	assertEquals("expected one vertex", Set.of("1"), graph.vertices());
    	graph.set("2", "3", 0);
    	assertEquals("expected vertices", Set.of("1", "2", "3"), graph.vertices());
    }
    
    /*
     * cover produced by add()
     * 	vertex included
     * 	partition on edges to vertex 0
     * 	partition on edges from vertex 0
     * 	partition on vertices number 1
     */
    @Test
    public void testRemove() {
    	Graph<String> graph = emptyInstance();
    	graph.add("1");
    	assertEquals("expected one vertex", Set.of("1"), graph.vertices());
    	assertTrue("expected remove from graph", graph.remove("1"));
    	assertEquals("expected no vertex", 0, graph.vertices().size());
    }
    
    /*
     * cover produced by set()
     * 	vertex not included
     * 	partition on edges to vertex 0
     * 	partition on edges from vertex 0
     * 	partition on vertices number 0
     */
    @Test
    public void testRemoveOnEmptyGraph() {
    	Graph<String> graph = emptyInstance();
    	assertFalse("expected no vertex removed from graph", graph.remove("3"));
    }
    

    /*
     * cover produced by set()
     * 	vertex included
     * 	partition on edges to vertex >1
     * 	partition on edges from vertex >1
     * 	partition on vertices number >1
     */
    public void testRemoveVetexWithMultipleEdge() {
    	Graph<String> graph = emptyInstance();
    	graph.set("1", "2", 1);
    	graph.set("3", "2", 1);
    	graph.set("2", "4", 1);
    	graph.set("2", "5", 1);
    	assertEquals("expected vertices", Set.of("1", "2", "3", "4", "5"), graph.vertices());
    	assertFalse("expected vertex removed from graph", graph.remove("2"));
    	assertEquals("expected vertices", Set.of("1", "3", "4", "5"), graph.vertices());
    	assertEquals("expected edges from source", 0, graph.targets("2").size());
    	assertEquals("expected edges to target", 0, graph.sources("2").size());
    }
    
    /*
     * cover vertex not included
     * 	partition on edges to target 0
     * 	partition on vertices number 0
     */
    @Test
    public void testSourceEmptyGraph() {
    	Graph<String> graph = emptyInstance();
    	assertEquals("expected no edges to target", 0, graph.sources("1").size());
    }
    
    /*
     * cover produced by set()
     * 	vertex included
     * 	partition on edges from source >1
     * 	partition on vertices number >1
     */
    @Test
    public void testTargetMultiEdge() {
    	Graph<String> graph = emptyInstance();
    	graph.set("5", "1", 1);
    	graph.set("5", "2", 2);
    	graph.set("5", "3", 3);
    	graph.set("5", "4", 4);
    	assertEquals("expected self loop", Map.of("1", 1, "2", 2, "3", 3, "4", 4), graph.targets("5"));
    }
}
