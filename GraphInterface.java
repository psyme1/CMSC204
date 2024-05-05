package com.example.project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A graph object G(V,E) contains a set V of vertices(towns) and a set E of
 * edges(roads). Each road connects vertex v1 to vertex v2; road(v1,v2).
 *
 */
public interface GraphInterface<V, E> {
    /**
     * Creates a new road in this graph by connecting sourceTown to destinationTown;
     * going from the sourceTown to the destinationTown, and returns the created
     * road.
     *
     * @param sourceTown      source town(vertex) of the road.
     * @param destinationTown destination town(vertex) of the road.
     * @param distance        distance of the road between sourceTown and
     *                        destinationTown
     * @param name            name of road
     *
     * @return The newly created road if added to the graph, otherwise null.
     *
     * @throws NullPointerException     if any of the specified towns(vertices) is
     *                                  null, message: Either source or destination
     *                                  town is null.
     * @throws IllegalArgumentException if either the source or destination towns is
     *                                  not already contained in this graph;
     *                                  message: Either source or destination town
     *                                  is not contained in the graph.
     */
    E addRoad(V sourceTown, V destinationTown, int distance, String name)
	    throws NullPointerException, IllegalArgumentException;

    /**
     * Adds the given road to the graph,
     * 
     * @param road the road instance to be added to the graph
     * @return the given road if added to the graph, otherwise null. *
     *
     */
    E addRoad(Road road);

    /**
     * Returns a road connecting source vertex(town) to target vertex(town), if such
     * vertices(towns) and such road exist in this graph. Otherwise returns null. If
     * any of the specified vertices(towns) is null returns null.
     *
     * In undirected graphs, the returned road may have its source and target
     * vertices in the opposite order; a road from town A to town B is equal to a
     * road from town B to town A
     *
     * @param sourceTown      source town(vertex) of the road.
     * @param destinationTown destination town(vertex)) of the road.
     *
     * @return a road connecting source town(vertex) to destination town(vertex).
     */
    E getRoad(V sourceTown, V destinationTown);

    /**
     * Adds the specified town(vertex) to this graph if not already present. Another
     * words, adds the specified town(vertex), v, to this graph if this graph
     * contains no town(vertex) u such that u.equals(v). If this graph already
     * contains such town(vertex), the graph will remain unchanged and the method
     * returns false.
     *
     * @param town town(vertex) to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     *         town(vertex), false otherwise.
     *
     * @throws NullPointerException if the specified town(vertex) is null; message:
     *                              Town is null.
     */
    boolean addTown(V town);

    /**
     * Returns true if this graph contains a road going from the source town(vertex)
     * to the destination town(vertex). In undirected graphs the same result is
     * obtained when source and destination are inverted. If any of the specified
     * towns(vertices) does not exist in the graph, or if is null, returns false.
     *
     * @param sourceTown      source town(vertex) of the road.
     * @param destinationTown destination town(vertex) of the road.
     *
     * @return true if this graph contains the specified road, false otherwise.
     */
    public boolean containsRoad(V sourceTown, V destinationTown);

    /**
     * Returns true if this graph contains the specified town(vertex); contains a
     * town(vertex) u such that u.equals(v). If the specified vertex is null returns
     * false.
     *
     * @param town town(vertex) whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified town(vertex).
     */
    boolean containsTown(V town);

    /**
     * Returns a set of the roads(edges) contained in this graph.
     *
     * @return a set of the roads(edges) contained in this graph.
     */
    Set<E> getRoads();

    /**
     * Returns a set of all roads(edges) touching the specified town(vertex);another
     * words adjacent vertices. If no roads(edges) are touching the specified
     * town(vertex) returns an empty set.
     *
     * @param town the town for which a set of touching roads is to be returned.
     *
     * @return a set of all roads touching the specified town(vertex).
     *
     * @throws IllegalArgumentException if town(vertex) is not found in the graph,
     *                                  message: Town does not exist in the graph.
     * @throws NullPointerException     if town(vertex) is null; message: Town is
     *                                  null.
     */
    Set<E> getRoadsOf(V town);

    /**
     * Removes an road(edge), if such road(edge) exist in this graph.
     * 
     * @param road the road to be removed.
     *
     * @return The removed road if removed, or null if no road removed.
     */
    E removeRoad(E road);
    
    /**
     * Removes a road going from source town to destination town, if such
     * towns and such road exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     *
     * @param sourceTown source town of the road.
     * @param destinationTown target town of the road.
     * @param weight weight of the road
     * @param description description of the road
     *
     * @return The removed road, or null if no road removed.
     */
     E removeRoad(V sourceVertex, V destinationVertex, int weight, String description);

    /**
     * Removes the specified town(vertex) from this graph including all its touching
     * roads(edges) if present. Another words, if the graph contains a town(vertex)
     * u such that u.equals(v), the call removes all roads(edges) that touch u and
     * then removes u itself. If no such u is found, the call leaves the graph
     * unchanged.
     *
     * @param town town(vertex) to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified town(vertex); false
     *         otherwise.
     */
    boolean removeTown(V town);

    /**
     * Returns a set of the towns(vertices) contained in this graph.
     *
     * @return a set view of the towns(vertices) contained in this graph.
     */
    Set<V> getSetOfTowns();

    /**
     * Returns the town(vertex) in the graph with the given name.
     *
     * @return the town(vertex) in the graph.
     */
    Town getTown(String name);

    /**
     * Find the shortest path from the sourceTown to the destinationTown. This
     * method calls the dijkstraShortestPath with the sourceTown. An example of an
     * element in the path is: Rockville via Glen Rd to Chevy Chase 8 ([sourceTown]
     * via [Road name] to [destinationTown] distance)
     * 
     * @param sourceTown      starting town(vertex)
     * @param destinationTown destination town(vertex)
     * @return An arraylist of String that describe the path from sourceTown to
     *         destinationTown
     */

    ArrayList<String> getShortestPath(V sourceTown, V destinationTown);

    /**
     * This method retrieves the shortest distance from the sourceTown to all the
     * other towns(vertices) in the graph. You can add additional attributes/methods
     * that you think this method could use and allow you to retrieve the path.
     * 
     * @param sourceTown the town(vertex) to find shortest path from
     * 
     */
    void dijkstraShortestPath(V sourceTown);
    
    /**
     * Creates an arraylist of all town names in sorted order by town name
     * 
     * @return an arraylist of all town names in sorted order by town name
     */
    ArrayList<String> getSortedListOfTowns();

    /**
     * Creates an arraylist of all roads in alphabetical order.
     * 
     * @return an arraylist of all roads in alphabetical order
     */
    ArrayList<String> getSortedListOfRoads();
    
    /**
     * Populates the graph using the data in the given file.
     * @param file the given file
     * @throws FileNotFoundException if file not found
     */
    void populateTownGraph(File file) throws FileNotFoundException;
    
}
