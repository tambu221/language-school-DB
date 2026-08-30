package it.uniroma2.bd.Model.Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lesson {
    private final int id_course;
    private final int id_professor;
    private final LocalTime start;
    private final LocalTime finish;
    private final LocalDate date;
    private final String name_course;
    
    public Lesson(int c,int p,LocalTime s,LocalTime f,LocalDate d){
        this.id_course=c;
        this.id_professor=p;
        this.start=s;
        this.finish=f;
        this.date=d;
        this.name_course=null;
    }
    public Lesson(int c,int p,LocalTime s,LocalTime f,LocalDate d,String name) {
    	this.id_course=c;
    	this.id_professor=p;
    	this.start=s;
    	this.finish=f;
    	this.date=d;
    	this.name_course=name;
    }
    
    public int getID_course() { return id_course;}
    public int getID_professor() { return id_professor;}
    public LocalTime getStart() { return start;}
    public LocalTime getFinish() { return finish;}
    public LocalDate getDate(){ return date;}
    public String getNameCourse() { return name_course;}
}

