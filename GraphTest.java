package com.example.project6;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraphTest {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[2], 2, "Road_1");
		  graph.addRoad(town[1], town[3], 4, "Road_2");
		  graph.addRoad(town[1], town[5], 6, "Road_3");
		  graph.addRoad(town[3], town[7], 1, "Road_4");
		  graph.addRoad(town[3], town[8], 2, "Road_5");
		  graph.addRoad(town[4], town[8], 3, "Road_6");
		  graph.addRoad(town[6], town[9], 3, "Road_7");
		  graph.addRoad(town[9], town[10], 4, "Road_8");
		  graph.addRoad(town[8], town[10], 2, "Road_9");
		  graph.addRoad(town[5], town[10], 5, "Road_10");
		  graph.addRoad(town[10], town[11], 3, "Road_11");
		  graph.addRoad(town[2], town[11], 6, "Road_12");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[2], town[11],6, "Road_12"), graph.addRoad(town[2], town[11], 6, "Road_12"));
		assertEquals(new Road(town[3], town[7],1, "Road_4"), graph.addRoad(town[3], town[7], 1, "Road_4"));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsRoad(town[3], town[5]));
		graph.addRoad(town[3], town[5], 1, "Road_13");
		assertEquals(true, graph.containsRoad(town[3], town[5]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_12");
		assertEquals(false, graph.containsTown(newTown));
		graph.addTown(newTown);
		assertEquals(true, graph.containsTown(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsRoad(town[2], town[11]));
		assertEquals(false, graph.containsRoad(town[3], town[5]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsTown(new Town("Town_2")));
		assertEquals(false, graph.containsTown(new Town("Town_12")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.getRoads();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_10", roadArrayList.get(1));
		assertEquals("Road_11", roadArrayList.get(2));
		assertEquals("Road_12", roadArrayList.get(3));
		assertEquals("Road_2", roadArrayList.get(4));
		assertEquals("Road_8", roadArrayList.get(10));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.getRoadsOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsRoad(town[2], town[11]));
		graph.removeRoad(town[2], town[11], 6, "Road_12");
		assertEquals(false, graph.containsRoad(town[2], town[11]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsTown(town[2]));
		graph.removeTown(town[2]);
		assertEquals(false, graph.containsTown(town[2]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.getSetOfTowns();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[10]));
		assertEquals(true, roads.contains(town[11]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[3]));
	}

	 @Test
	  public void testTown_1ToTown_11() {
		  String beginTown = "Town_1", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.getSetOfTowns();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.getShortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
			  assertEquals("Town_2 via Road_12 to Town_11 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown1ToTown_10() {
		  String beginTown = "Town_1", endTown = "Town_10";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.getSetOfTowns();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.getShortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  for(String str : path){
				  System.out.println(str);
			  }
			  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
			  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testTown_4ToTown_11() {
		  String beginTown = "Town_4", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.getSetOfTowns();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.getShortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_4 via Road_6 to Town_8 3 mi",path.get(0).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(1).trim());
			  assertEquals("Town_10 via Road_11 to Town_11 3 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
