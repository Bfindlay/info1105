package week8;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class TravelDestinationsTest {

	@Test
	public void testGetDirectDestinations() {
		Graph<String, Integer> graph = new AdjacencyMapGraph<String, Integer>(true);

		Vertex<String> aus = graph.insertVertex("Australia");
		Vertex<String> uk = graph.insertVertex("UK");
		Vertex<String> france = graph.insertVertex("France");
		Vertex<String> germany = graph.insertVertex("Germany");
		Vertex<String> usa = graph.insertVertex("USA");

		graph.insertEdge(aus, usa, 1);
		graph.insertEdge(aus, uk, 1);
		graph.insertEdge(uk, france, 1);
		graph.insertEdge(uk, aus, 1);
		graph.insertEdge(usa, france, 1);
		graph.insertEdge(usa, germany, 1);

		TravelDestinations destinations = new TravelDestinations(graph);

		List<String> dest = destinations.getDirectDestinations("Australia");
		List<String> destF = destinations.getDirectDestinations("France");
		List<String> empty = destinations.getDirectDestinations("China");
		List<String> destUS = destinations.getDirectDestinations("USA");

		assertEquals(0, empty.size());
		assertEquals(2, dest.size());

		assertEquals(0, destF.size());

		assertEquals(2, destUS.size());

	}

}
