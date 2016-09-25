package week8;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
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
				Iterable<Edge<Integer>> ites = graph.outgoingEdges(ver);
				Iterator<Edge<Integer>> it = ites.iterator();
				while (it.hasNext()) {
					Edge<Integer> edge = it.next();
					if (edge != null) {
						if (graph.endVertices(edge) != null && graph.endVertices(edge).length > 0) {
							Vertex<String>[] found = graph.endVertices(edge);
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
		visited = search(country, visited, country);

		visited.forEach(v -> System.out.println(v));
		return visited;
	}

	public List<String> search(String vert, List<String> visited, String start) {
		// System.out.println("called search on " + vert);
		// check if vert is in the visitd list
		if (visited.contains(vert)) {
			// System.out.println("ended");
			return visited;
		}
		// System.out.println("added to visited " + vert);
		if (vert != start)
			visited.add(vert);
		for (String s : getDirectDestinations(vert)) {
			search(s, visited, start);
		}
		return visited;
	}

	/* Exercise 3 methods */

	/**
	 * Return the country ('destinationA' or 'destinationB') which requires
	 * fewer flights to travel to from country 'current'
	 */
	public String closerDestination(String current, String destinationA, String destinationB) {

		return null;
	}

	public String cheapestDirectFlight(String fromCountry) {
		String value = null;
		for (Vertex<String> vert : graph.vertices()) {
			if (vert.getElement().equals(fromCountry)) {
				int cost = Integer.MAX_VALUE;
				for (Edge<Integer> edge : graph.outgoingEdges(vert)) {
					if (edge.getElement() < cost) {
						cost = edge.getElement();
						value = graph.opposite(vert, edge).getElement();
					}
				}
				return value;
			}
		}
		return null;
	}

	/**
	 * Return the minimal cost to get to toCountry from fromCountry. If
	 * fromCountry cannot be reached, then return Integer.MAX_VALUE
	 */
	public int shortestPathCost(String fromCountry, String toCountry) {
		Vertex<String> origin = null, destination = null;
		for (Vertex<String> vert : graph.vertices()) {
			if (vert.getElement().equals(fromCountry)) {
				origin = vert;
			}
			if (vert.getElement().equals(toCountry)) {
				destination = vert;
			}
		}

		HashMap<Vertex<String>, Integer> map = new HashMap<Vertex<String>, Integer>();
		PriorityQueue<SimpleEntry<Integer, Vertex<String>>> queue = new PriorityQueue<SimpleEntry<Integer, Vertex<String>>>(
				Comparator.comparing(SimpleEntry::getKey));
		int i = 0;
		for (Vertex<String> v : graph.vertices()) {
			if (v == origin) {
				map.put(v, 0);
				queue.add(new SimpleEntry<Integer, Vertex<String>>(0, v));
			} else {
				map.put(v, Integer.MAX_VALUE);
				queue.add(new SimpleEntry<Integer, Vertex<String>>(i, v));
				i++;
			}
		}

		while (!queue.isEmpty()) {
			Vertex<String> u = queue.remove().getValue();
			for (Edge<Integer> edge : graph.outgoingEdges(u)) {
				Vertex<String> v = graph.opposite(u, edge);
				if (map.get(u) < (map.get(v) - edge.getElement())) {
					map.replace(v, edge.getElement() + map.get(u));
					queue.add(new SimpleEntry<Integer, Vertex<String>>(queue.size() - 1, v));
				}
			}
		}
		if (!(map.get(destination) == null)) {
			return map.get(destination);
		} else {
			return Integer.MAX_VALUE;
		}
	}

}
