package com.example.project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph implements GraphInterface<Town, Road>{
    private Set<Road> roads;
    private Set<Town> towns;

    public Graph(){
        roads = new HashSet<>();
        towns = new HashSet<>();
    }


    @Override
    public Road addRoad(Town sourceTown, Town destinationTown, int distance, String name) throws NullPointerException, IllegalArgumentException {
        if(sourceTown == null || destinationTown == null) throw new NullPointerException("Either source or destination town is null.");
        if(!containsTown(sourceTown) || !containsTown(destinationTown)) throw new IllegalArgumentException("Either source or destination town is not contained in the graph.");
        Road r = new Road(sourceTown, destinationTown, distance, name);
        roads.add(r);
        return r;
    }

    @Override
    public Road addRoad(Road road) {
        roads.add(road);
        return road;
    }

    @Override
    public Road getRoad(Town sourceTown, Town destinationTown) {
        if(sourceTown == null || destinationTown == null) return null;
        for(Road r : roads){
            if((r.getDestination().equals(destinationTown) && r.getSource().equals(sourceTown)) ||
                    (r.getDestination().equals(sourceTown) && r.getSource().equals(destinationTown))){
                return r;
            }
        }
        return null;
    }

    @Override
    public boolean addTown(Town town) {
        if(town == null) throw new NullPointerException("Town is null.");
        if(!containsTown(town)){
            towns.add(town);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsRoad(Town sourceTown, Town destinationTown) {
        for(Road r : roads){
            if(Objects.equals(r.getSource(), sourceTown)){
                if(Objects.equals(r.getDestination(), destinationTown)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsTown(Town town) {
        for(Town t : towns){
            if(t.equals(town)) return true;
        }
        return false;
    }

    @Override
    public Set<Road> getRoads() {
        return new HashSet<>(roads);
    }

    @Override
    public Set<Road> getRoadsOf(Town town) {
        if(town == null) throw new NullPointerException("Town is null.");
        if(!containsTown(town)) throw new IllegalArgumentException("Town does not exist in the graph.");
        Set<Road> rs = new HashSet<>();
        for(Road r : roads){
            if(r.contains(town)) rs.add(r);
        }
        return rs;
    }

    @Override
    public Road removeRoad(Road road) {
        for(Road r : roads){
            if(r.equals(road)) return r;
        }
        return null;
    }

    @Override
    public Road removeRoad(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        Iterator<Road> iterator = roads.iterator();
        while(iterator.hasNext()){
            Road r = iterator.next();
            if(r.equals(road)){iterator.remove(); return r;}
        }
        return null;
    }

    @Override
    public boolean removeTown(Town town) {
        Iterator<Town> iterator = towns.iterator();
        while(iterator.hasNext()){
            Town t = iterator.next();
            if(t.equals(town)){ iterator.remove(); return true;}
        }
        return false;
    }

    @Override
    public Set<Town> getSetOfTowns() {
        return new HashSet<>(towns);
    }

    @Override
    public Town getTown(String name) {
        for(Town t : towns){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getShortestPath(Town sourceTown, Town destinationTown) {
        if(sourceTown == null || destinationTown == null) return null;
        if(sourceTown.equals(destinationTown)) return new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        dijkstraShortestPath(sourceTown);
        Town t = destinationTown;
        if(t.getPredecessorNode() == null){
            return null;
        }
        while(true){
            Road r = getRoad(t.getPredecessorNode(), t);
            result.add(t.getPredecessorNode().getName() + " via " + r.getName() + " to " + t.getName() + " " + r.getDistance() + " mi");
            if(t.getPredecessorNode().equals(sourceTown)){
                break;
            }
            t = t.getPredecessorNode();
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public void dijkstraShortestPath(Town sourceTown) {
        ArrayList<Town> visited = new ArrayList<>();
        visited.add(sourceTown);
        for(Town t : towns){
            t.setShortestDistance(Integer.MAX_VALUE);
            t.setPredecessorNode(null);
        }
        ArrayList<Town> unvisited = new ArrayList<>(towns);
        unvisited.remove(sourceTown);
        PriorityQueue<Town> upcoming = new PriorityQueue<>();
        sourceTown.setShortestDistance(0);
        sourceTown.setPredecessorNode(null);
        upcoming.add(sourceTown);
        while(!upcoming.isEmpty()) {
            Town t = upcoming.poll();
            Set<Road> rs = getRoadsOf(t);
            for(Road r : rs){

                Town des = null;
                if(r.getDestination().equals(t)){
                        des = getTown(r.getSource().getName());
                }else {
                    des = getTown(r.getDestination().getName());
                }
                if(t.getShortestDistance() + r.getDistance() < des.getShortestDistance()){
                    des.setShortestDistance(r.getDistance() + t.getShortestDistance());
                    des.setPredecessorNode(t);
                    unvisited.remove(des);
                    visited.add(des);
                    upcoming.add(des);
                }
            }
        }
    }

    @Override
    public ArrayList<String> getSortedListOfTowns() {
        ArrayList<Town> sortedTowns = new ArrayList<>(towns);
        Collections.sort(sortedTowns);
        ArrayList<String> strs = new ArrayList<>();
        for(Town t : sortedTowns){
            strs.add(t.getName());
        }
        return strs;
    }

    @Override
    public ArrayList<String> getSortedListOfRoads() {
        ArrayList<Road> sortedRoads = new ArrayList<>(roads);
        Collections.sort(sortedRoads);
        ArrayList<String> strs = new ArrayList<>();
        for(Road r : sortedRoads){
            strs.add(r.getName());
        }
        return strs;
    }

    @Override
    public void populateTownGraph(File file) throws FileNotFoundException {
        if(!file.exists()) throw new FileNotFoundException();
        Scanner s = new Scanner(file);
        while(s.hasNext()){
            String str = s.nextLine();
            String[] places = str.split("[;,]");
            Town t1 = getTown(places[2]);
            if(!towns.contains(new Town(places[2]))){
                t1 = new Town(new Town(places[2]));
            }
            Town t2 = getTown(places[3]);
            if(!towns.contains(new Town(places[3]))){
                t2 = new Town(new Town(places[3]));
            }
            int distance = Integer.parseInt(places[1]);
            addTown(t1); addTown(t2);
            Road road = new Road(t1, t2, distance, places[0]);
            addRoad(road);
        }
    }
}
