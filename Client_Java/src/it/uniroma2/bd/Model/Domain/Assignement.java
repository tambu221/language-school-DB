package it.uniroma2.bd.Model.Domain;

import java.time.LocalDate;

public class Assignement {
     private final int id_student;
     private final int id_course;
     private final LocalDate date;


     public Assignement(int s,int c,LocalDate d){
         this.id_student=s;
         this.id_course =c;
         this.date=d;
     }

    public int getId_student(){ return id_student;}
    public int getId_course(){ return id_course;}
    public LocalDate getDate(){ return date;}
}

