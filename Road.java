package com.example.project6;

import java.util.Objects;

public class Road implements Comparable<Road>{

    private final String name;
    private final Town source;
    private final Town destination;
    private final int distance;

    public Road(Town source, Town destination, String name){
        this(source, destination, 10, name);
    }
    public Road(Town source, Town destination, int distance, String name){
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public boolean contains(Town town){
        return town.equals(destination) || town.equals(source);
    }

    public String getName(){return name;}
    public Town getSource(){return source;}
    public Town getDestination(){return destination;}
    public int getDistance(){return distance;}

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Road o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!Objects.equals(this.getClass(), o.getClass())) return false;
        Road road = (Road) o;
        if(!this.contains(road.source) && !this.contains(road.destination)){
            System.out.println("yeah");
            return false;
        }
        return true;
    }

}
