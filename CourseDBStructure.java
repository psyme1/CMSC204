package com.example.cmscassignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{
    private LinkedList<CourseDBElement>[] list;
    private int size;
    public CourseDBStructure(String numElements, int size){
        this.size = size;
        list = new LinkedList[size];
    }
    public CourseDBStructure(int num){
        num = (int) Math.round(num / 1.5);
        size = get4KPrime(num);
        list = new LinkedList[size];
    }
    @Override
    public void add(CourseDBElement element) {
        if(list[element.hashCode() % size] == null){
            list[element.hashCode() % size] = new LinkedList<>();
        }
        list[element.hashCode() % size].add(element);
    }

    @Override
    public CourseDBElement get(int crn) throws IOException {
        if(list[crn % size].isEmpty()) throw new IOException();
        return list[crn % size].getFirst();
    }

    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> result = new ArrayList<>();
        for(LinkedList<CourseDBElement> ll : list){
            if(ll == null) continue;
            for(CourseDBElement element : ll){
                String out = "Course:" + element.getId() + " CRN:" + element.getCRN()
                        + " Credits:" + element.getCredits() + " Instructor:" + element.getInstructor() + " Room:" + element.getRoomNum() + "\n";
                result.add(out);
            }

        }
        return result;
    }

    @Override
    public int getTableSize() {
        return size;
    }

    public static int get4KPrime(int crn){
        int x = 0;
        while(4 * x + 3 <= crn || !isPrime(4 * x + 3)){x++;}
        return 4 * (x) + 3;
    }

    private static boolean isPrime(int num){
        if(num <= 1) return false;
        for(int i = 2; i < num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }

}
