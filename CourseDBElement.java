package com.example.cmscassignment4;

public class CourseDBElement {
    private String id;
    private int crn;
    private int credits;
    private String roomNum;
    private String instructor;
    public CourseDBElement(){

    }
    public CourseDBElement(CourseDBElement element){
        this.instructor = element.instructor;
        this.id = element.id;
        this.crn = element.crn;
        this.credits = element.credits;
        this.roomNum = element.roomNum;
    }
    public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor){
        this.instructor = instructor;
        this.id = id;
        this.crn = crn;
        this.credits = credits;
        this.roomNum = roomNum;
    }
    @Override
    public boolean equals(Object anotheObject){
        if(this == null && anotheObject == null) return true;
        if(!(anotheObject instanceof CourseDBElement) || anotheObject.hashCode() != this.hashCode()) return false;
        CourseDBElement other = (CourseDBElement) anotheObject;
        if(!other.roomNum.equals(this.roomNum)) return false;
        if(this.crn != other.crn) return false;
        if(this.credits != other.credits) return false;
        if(!this.id.equals(other.id)) return false;
        return this.instructor.equals(other.instructor);
    }

    public int getCRN(){return this.crn;}
    public int getCredits(){return this.credits;}
    public String getId(){return this.id;}
    public String getRoomNum(){return this.roomNum;}
    public String getInstructor(){return this.instructor;}
    public void setId(String id){this.id = id;}
    public void setCRN(int crn){this.crn = crn;}
    public void setCredits(int c){this.credits = c;}
    public void setRoomNum(String room){this.roomNum = room;}
    public void setInstructor(String instructor){this.instructor = instructor;}
    @Override
    public int hashCode(){
        return crn;
    }
    @Override
    public String toString(){
        String out = "Course:" + getId() + " CRN:" + getCRN()
                + " Credits:" + getCredits() + " Instructor:" + getInstructor() + " Room:" + getRoomNum();
        return out;
    }
}
