package com.example.project6;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Town implements Comparable<Town>{

    private final String name;
    private final Set<Town> adjacentTowns;
    private Town predecessor;
    private int shortestDistance;

    public Town(String name){
        this.name = name;
        adjacentTowns = new HashSet<>();
        this.predecessor = null;
        this.shortestDistance = Integer.MAX_VALUE;
    }

    public Town(Town town){
        this.name = town.name;
        this.adjacentTowns = new HashSet<>(town.adjacentTowns);
        this.predecessor = town.predecessor;
        this.shortestDistance = town.shortestDistance;
    }

    public Set<Town> getAdjTowns(){
        return adjacentTowns;
    }

    public String getName(){
        return this.name;
    }

    public void addAdjTown(Town town){
        this.adjacentTowns.add(town);
    }

    public Town getPredecessorNode(){return this.predecessor;}
    public int getShortestDistance(){return this.shortestDistance;}
    public void setPredecessorNode(Town predecessorNode){this.predecessor = predecessorNode;}
    public void setShortestDistance(int shortestDistance){this.shortestDistance = shortestDistance;}


    @Override
    public int compareTo(Town town) {
        return this.getName().compareTo(town.name);
    }


    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Town)) return false;
        Town t = (Town) o;
        return Objects.equals(this.getName(), t.getName());
    }

    @Override
    public String toString(){
        return this.name;
    }


}
