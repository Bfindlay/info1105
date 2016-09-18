package week8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TravelDestinations<V> {

	private Graph<String, Integer> graph;

	public TravelDestinations(Graph<String, Integer> graph) {
		this.graph = graph;
		// TODO: implement this method
	}

	/* Exercise 1 methods */

	/**
	 * Return all the countries that are a single direct flight away from the
	 * given country, in any order. If no flights depart this country, return an
	 * empty List.
	 * 
	 * @param <V>
	 */

	public List<String> getDirectDestinations(String str) {

		List<String> verts = new ArrayList<>();
		Iterable<Vertex<String>> ite = graph.vertices();
		Iterator<Vertex<String>> iter = ite.iterator();
		while (iter.hasNext()) {
			Vertex<String> ver = iter.next();
			// System.out.println(ver.getElement());
			if (ver.getElement().equals(str)) {
				System.out.println("Found the vertex " + ver.getElement());
				Iterable<Edge<Integer>> ites = graph.outgoingEdges(ver);
				Iterator<Edge<Integer>> it = ites.iterator();
				while (it.hasNext()) {
					Edge<Integer> edge = it.next();
					if (edge != null) {
						if (graph.endVertices(edge) != null && graph.endVertices(edge).length > 0) {
							Vertex<String>[] found = graph.endVertices(edge);
							System.out.println(found[1].getElement());
							verts.add(found[1].getElement());
						}
					}
				}
			}
		}
		return verts;
	}

	/**
	 * Return true if there is a direct flight from 'fromCountry' to
	 * 'toCountry'. Otherwise, return false.
	 */
	public boolean isDirectFlight(String fromCountry, String toCountry) {
		List<String> dest = getDirectDestinations(fromCountry);

		List<String> res = dest.stream().filter(d -> d == toCountry).collect(Collectors.toList());
		return (res.size() > 0) ? true : false;

	}

	/* Exercise 2 methods */
	/**
	 * Return all the countries that are reachable from the given country, using
	 * any number of flights (for example, if we can fly from A to B, then from
	 * B to C, then we can say that both B and C are reachable from A.
	 */
	public List<String> getReachableDestinations(String country) {
		// TODO: implement this method

		List<String> visited = new ArrayList<>();
		search(null, visited);

		return visited;
	}

	public void search(Vertex<String> vert, List<String> visited) {
		// check if vert is in the visitd list
		List<String> res = visited.stream().filter(d -> d == vert.getElement())
				.collect(Collectors.toList());
		// end search if it is found in the list
		if (res.size() > 0)
			return;
		visited.add(vert.getElement());
		for (String s : getReachableDestinations(vert.getElement())) {
			search(s, visited);
		}

	}

	/* Exercise 3 methods */

	/**
	 * Return the country ('destinationA' or 'destinationB') which requires
	 * fewer flights to travel to from country 'current'
	 */
	public String closerDestination(String current, String destinationA, String destinationB) {
		// TODO: implement this method
		return null;
	}
}
