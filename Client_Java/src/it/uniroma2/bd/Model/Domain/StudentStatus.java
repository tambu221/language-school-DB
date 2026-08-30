package it.uniroma2.bd.Model.Domain;

public class StudentStatus {
     private  int id_lesson;
     private  int id_student;
     private boolean status;


    public StudentStatus(int id_l,int id_s,boolean s){
        this.id_lesson = id_l;
        this.id_student = id_s;
        this.status = s;

    }

    public int getId_lesson() { return id_lesson;}
    public int getId_student() { return id_student;}
    public boolean get_status(){ return status;}
}
