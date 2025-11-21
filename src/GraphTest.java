// --== CS400 File Header Information ==--
// Name: <Khyati Gupta>
// Email: <kgupta44@wisc.edu>
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

	private CS400Graph<Integer> graph;
	private CS400Graph<Integer> graphAlias;

	@BeforeEach
	/**
	 * Instantiate graph from last week's shortest path activity.
	 */
	public void createGraph() {
		graph = new CS400Graph<>();
		// insert verticies 0-9
		for (int i = 0; i < 10; i++)
			graph.insertVertex(i);
		// insert edges from Week 08. Dijkstra's Activity
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 7, 2);
		graph.insertEdge(1, 8, 4);
		graph.insertEdge(2, 4, 4);
		graph.insertEdge(2, 6, 3);
		graph.insertEdge(3, 1, 6);
		graph.insertEdge(3, 7, 2);
		graph.insertEdge(4, 5, 4);
		graph.insertEdge(5, 0, 2);
		graph.insertEdge(5, 1, 4);
		graph.insertEdge(5, 9, 1);
		graph.insertEdge(6, 3, 1);
		graph.insertEdge(7, 0, 3);
		graph.insertEdge(7, 6, 1);
		graph.insertEdge(8, 9, 3);
		graph.insertEdge(9, 4, 5);
	}

	/**
	 * Checks the distance/total weight cost from the vertex labelled 0 to 8 (should
	 * be 15), and from the vertex labelled 9 to 8 (should be 17).
	 */
	@Test
	public void providedTestToCheckPathCosts() {

		assertTrue(graph.getPathCost(0, 8) == 15);
		assertTrue(graph.getPathCost(9, 8) == 17);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 0 to 8, and from the vertex labelled 9 to 8.
	 */
	@Test
	public void providedTestToCheckPathContents() {
		
		assertTrue(graph.shortestPath(0, 8).toString().equals("[0, 2, 6, 3, 1, 8]"));
		assertTrue(graph.shortestPath(9, 8).toString().equals("[9, 4, 5, 1, 8]"));
	}
	
	/**
	 * Test method to confirm that the distance that was reported as #3 from last
	 * week's activity is correct.
	 */
	@Test
	public void testLongestShortestDistance() {

		int[] costs = new int[10];

		for (int i = 0; i < 10; i++)
			costs[i] = graph.getPathCost(5, i);

		int maxDistance = 0;
		int target = 0;
		;

		for (int i = 0; i < 9; i++) {

			if (costs[i] < costs[i + 1]) {
				maxDistance = costs[i + 1];
				target = i + 1;
			}
		}

		assertTrue(maxDistance == 8);
		assertTrue(target == 8);

	}

	/**
	 * Test method to confirm the sequence of values along the path from your source
	 * node to this same end node (the end node that is furthest from your source
	 * node).
	 */
	@Test
	public void testLongestShortestPath() {

		assertTrue(graph.shortestPath(5, 8).toString().equals("[5, 1, 8]"));
	}

	/**
	 * Tests to see if no such element exception is thrown when either the start or
	 * end vertex is non-existent in the graph;
	 */
	@Test
	public void testExceptionNull() {

		assertThrows(NoSuchElementException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				graph.shortestPath(5, 10);
			}
		});

		assertThrows(NoSuchElementException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				graph.shortestPath(10, 1);
			}
		});
	}

	/**
	 * Tests to see if no such element exception is thrown when no path exists from
	 * the given start and end vertices.
	 */
	@Test
	public void testExceptionPath() {

		graphAlias = new CS400Graph<>();

		for (int i = 0; i < 6; i++)
			graphAlias.insertVertex(i);

		graphAlias.insertEdge(0, 2, 1);
		graphAlias.insertEdge(1, 3, 2);
		graphAlias.insertEdge(1, 4, 4);
		graphAlias.insertEdge(2, 1, 4);
		graphAlias.insertEdge(2, 4, 3);
		graphAlias.insertEdge(3, 1, 6);
		graphAlias.insertEdge(4, 2, 4);
		graphAlias.insertEdge(5, 0, 2);

		assertThrows(NoSuchElementException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				graphAlias.shortestPath(2, 5);
			}
		});
	}

}