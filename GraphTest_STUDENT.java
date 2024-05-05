package com.example.project6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

public class GraphTest_STUDENT {

    Graph graph = null;
    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        Town t1 = new Town("Germantown");
        graph.addTown(t1);
        Town t2 = new Town("Damascus");
        graph.addTown(t2);
        Town t3 = new Town("Olney");
        graph.addTown(t3);
        Town t4 = new Town("Rockville");
        graph.addTown(t4);
        Town t5 = new Town("Brookeville");
        graph.addTown(t5);
        Town t6 = new Town("Clarksburg");
        graph.addTown(t6);
        Town t7 = new Town("Poolesville");
        graph.addTown(t7);
        Town t8 = new Town("Takoma");
        graph.addTown(t8);
        Town t = new Town("Bethesda");
        graph.addTown(t);
        t = new Town("Potomac");
        graph.addTown(t);
        t = new Town("Boyds");
        graph.addTown(t);
        t = new Town("Darnestown");
        graph.addTown(t);
        t = new Town("Fredrick");
        graph.addTown(t);

        graph.addRoad(t1, t2, 4, "Road_1");
        graph.addRoad(t3, t2, 1, "Road_2");
        graph.addRoad(t5, t2, 7, "Road_3");
        graph.addRoad(t6, t4, 5, "Road_4");

    }

    @AfterEach
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddRoadTownTownIntString() {
	    try{
            Town a = new Town("New York City");
            Town b = new Town("Glen Mont");
            Road r = graph.addRoad(a, b, 3, "FakeRoad");
            fail("Neither town are in the graph");
        }catch (IllegalArgumentException ignored){
            // should throw an error
        }
        catch(Exception ex){
            fail("You threw the wrong exception: " + ex.getClass().getName());
        }

        try{
            Town a = new Town("New York City");
            Town b = new Town("Olney");
            Road r = graph.addRoad(a, b, 19, "FakeRoad");
            fail("New York City is not in the graph");
        }catch (IllegalArgumentException ignored){
            // should give an exception
        }
        catch(Exception ex){
            fail("You threw the wrong exception: " + ex.getClass().getName());
        }

        try{
            graph.addRoad(new Town("Olney"), new Town("Boyds"), 18, "TrueRoad");
        }catch(IllegalArgumentException ex){
            fail("The arguments are valid");
        }
        catch(NullPointerException ex){
            fail("Neither argument was null");
        }
        catch (Exception ex){
            fail("Method threw an argument it wasn't supposed to: " + ex.getClass().getName());
        }

        try{
            graph.addRoad(null, new Town("Rockville"), 22, "NullRoad");
        }catch(NullPointerException ex){
            // Correct exception thrown
        }
        catch (Exception ex){
            fail("You threw the wrong exception: " + ex.getClass().getName());
        }
    }

    @Test
    public void testAddRoadRoad() {
	    Road badRoad = new Road(new Town("Cape May"), new Town("Buffalo"), 9999,"i-2000");
        try {
            graph.addRoad(badRoad);
        }catch(Exception ex){
            fail("This shouldn't throw an exception");
        }
    }
    
    @Test
    public void testAddRoadWithNullSource() {
	    try{
            graph.addRoad(null, new Town("Olney"), 30, "Netherland");
            fail("No exception thrown");
        }catch(NullPointerException ex){
            // correctly done
        }
        catch (Exception ex){
            fail("Wrong exception thrown");
        }
    }
    
    @Test
    public void testAddRoadWithNullDestination() {
        try{
            graph.addRoad(new Town("Olney"), null, 15, "Narnia");
            fail("No exception thrown");
        }catch(NullPointerException ex){
            // correctly done
        }
        catch (Exception ex){
            fail("Wrong exception thrown " + ex.getClass().getName());
        }
    }
    
    @Test
    public void testAddRoadSourceNotExists() {
	    try{
            graph.addRoad(new Town("Williamsberg"), new Town("Olney"), 350, "Idon'tknow");
        }catch(IllegalArgumentException ex){
            // correct
        }
        catch (Exception ex){
            fail("Wrong exception thrown: " + ex.getClass().getName());
        }
    }

    @Test
    public void testGetRoad() {
	    Road r = graph.getRoad(new Town("Olney"), new Town("Damascus"));
        if(r.getDistance() != 1) fail("Wrong distance");
    }

    @Test
    public void testAddTown() {
	    try{
            graph.addTown(new Town("New York City"));
            Town t = graph.getTown("New York City");
            if(t == null || !t.getName().equals("New York City")){
                throw new Exception("Name wasn't New York");
            }
        }catch(Exception ex){
            fail("this shouldn't throw and exception " + ex.getMessage());
        }
    }

    @Test
    public void testContainsRoad() {
	    try{
            if(!graph.containsRoad(new Town("Olney"), new Town("Damascus"))) throw new Exception("Does not contain road");
        }catch(Exception ex){
            fail("This shouldn't throw an exception " + ex.getMessage());
        }
    }

    @Test
    public void testContainsTown() {
	    if(graph.containsTown(new Town("New York City"))) fail("Contains town not in graph");
        if(!graph.containsTown(new Town("Olney"))) fail("Doesn't contain town in graph");
    }

    @Test
    public void testGetTown() {
	    if(!graph.getTown("Olney").getName().equals("Olney")) fail("failed");
    }

    @Test
    public void testGetRoads() {
        Set<Road> roads = graph.getRoads();
        if(roads.size() != 4) fail("Wrong");
    }

    @Test
    public void testGetRoadsOf() {
        if(graph.getRoadsOf(new Town("Olney")).size() != 1) fail("Wrong");
    }

    @Test
    public void testGetRoadsOfNullTown() {
        try{
            graph.getRoadsOf(null);
            fail("Didn't throw an exception");
        }catch(NullPointerException ex){
            // Correct
        }catch (Exception ex){
            fail("Wrong exception thrown " + ex.getClass());
        }
    }

    @Test
    public void testGetRoadsOfTownNotExist() {
	    try {
            graph.getRoadsOf(new Town("Annapolis"));
        }catch(IllegalArgumentException ignored){
            // correct
        }catch(Exception ex){
            fail("Wrong exception thrown " + ex.getClass().getName());
        }
    }

    @Test
    public void testRemoveRoad() {
	    graph.removeRoad(new Town("Damascus"), new Town("Germantown"), 4, "Road_1");
        if(graph.containsRoad(new Town("Damascus"), new Town("Germantown"))){
            fail("Still has road");
        }
    }

    @Test
    public void testRemoveTown() {
        graph.removeTown(new Town("Damascus"));
        if(graph.containsTown(new Town("Damascus"))){
            fail("Still has road");
        }
    }

    @Test
    public void testGetSetOfTowns() {
        String[] correct = new String[]{"Bethesda", "Boyds", "Brookeville", "Clarksburg", "Damascus", "Darnestown", "Fredrick", "Germantown", "Olney", "Poolesville", "Potomac", "Rockville", "Takoma"};
        List<String> out = graph.getSortedListOfTowns();
        for(int i = 0; i < correct.length; i++){
            if(!(correct[i].equals(out.get(i)))){
                fail("Not sorted correct");
            }
        }
    }

    
    /**************** You must have a test case for a source town to every other town of your test graph 
     * for example; testShortestPathFromTownAToTownB
     */
    @Test
    public void testShortestPathFromTownAToTownB() {

    }
    
    @Test
    public void testPopulateTownGraph() {
	fail("Not yet implemented");	
    }

}
