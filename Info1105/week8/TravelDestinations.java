package week8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	public List<String> getDirectDestinations(String str) {

		List<String> verts = new ArrayList<>();
		Iterable<Vertex<String>> ite = graph.vertices();
		Iterator<Vertex<String>> iter = ite.iterator();
		while (iter.hasNext()) {
			Vertex<String> ver = iter.next();
			// System.out.println(ver.getElement());
			if (ver.getElement().equals(str)) {
				System.out.println("Found the vertex");
				Iterable<Edge<Integer>> ites = graph.outgoingEdges(ver);
				Iterator<Edge<Integer>> it = ites.iterator();
				while (it.hasNext()) {
					Edge<Integer> edge = it.next();
					Vertex<String>[] found = graph.endVertices(edge);
					System.out.println(found[0].getElement());
					verts.add(found[0].getElement());
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
		// TODO: implement this method
		return false;
	}

	/* Exercise 2 methods */
	/**
	 * Return all the countries that are reachable from the given country, using
	 * any number of flights (for example, if we can fly from A to B, then from
	 * B to C, then we can say that both B and C are reachable from A.
	 */
	public List<String> getReachableDestinations(String country) {
		// TODO: implement this method
		return null;
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
