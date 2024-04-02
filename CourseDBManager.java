package com.example.cmscassignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{
    private CourseDBStructure db;
    public CourseDBManager(){
        db = new CourseDBStructure(10);
    }
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
        db.add(element);
    }

    @Override
    public CourseDBElement get(int crn) {
        try {
            return db.get(crn);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void readFile(File input) throws FileNotFoundException {
        if(!input.exists()) throw new FileNotFoundException();
        Scanner s = new Scanner(input);
        while(s.hasNext()){
            try {
                String[] serializeOrder = {"Course", "CRN", "Credits", "Instructor", "Room"};
                String line = s.nextLine();
                String[] parts = line.split("[ :]");
                String id = "", roomNum = "", instructor = "";
                int crn = -1, credits = -1;
                if(line.startsWith(serializeOrder[0])){
                    StringBuilder b = new StringBuilder();
                    String tracker = "";
                    int i = 0;
                    int j = 1;
                    while(true){
                        if(j != parts.length) {
                            tracker = parts[j];
                            if(i+1 == serializeOrder.length || !parts[j].startsWith(serializeOrder[i+1])){
                                if(!parts[j].equals(" ")){
                            b.append(parts[j]); if(i != 1 && i != 2 && j+1 < parts.length && !parts[j+1].startsWith(serializeOrder[i+1])) b.append(" ");}}
                            j++;
                        }
                        if((i+1 != serializeOrder.length && tracker.startsWith(serializeOrder[i + 1])) || (i == 4 && j == parts.length)){
                            switch (i) {
                                case 0 -> id = b.toString();
                                case 1 -> crn = Integer.parseInt(b.toString());
                                case 2 -> credits = Integer.parseInt(b.toString());
                                case 3 -> instructor = b.toString();
                                case 4 -> roomNum = b.toString();
                                default -> { System.out.println("oops");}
                            }
                            b = new StringBuilder();
                            i++;
                            if(i == 5) break;
                        }
                    }
                }else {
                    if(parts.length > 4) {
                        id = parts[0];
                        crn = Integer.parseInt(parts[1]);
                        credits = Integer.parseInt(parts[2]);
                        roomNum = parts[3];
                        instructor = parts[4];
                    }else{
                        continue;
                    }
                }
                add(id, crn, credits, roomNum, instructor);
            }catch(Exception ignored){
                // This happens if a line has an incorrect format
                // could be a wrong parse exception
                // or if they don't give enough data points
                // one issue is that if someone wrote the instructor as like Ms. A with a space the name would only show as Ms.
                // it would be efficient to use '_' as spaces and then do String#replace('_', ' '); but the instructions don't say to do that
            }
        }
    }

    @Override
    public ArrayList<String> showAll() {
        return db.showAll();
    }
}
