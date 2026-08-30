package it.uniroma2.bd.Model.Domain;

public class Report {

    public int id_professor;
    public String name_professor;
    public int id_course;
    public String course_name;
    public int number_lessons;

    public Report(int p,String np,int c,String nc,int nl){
        this.id_professor = p;
        this.name_professor = np;
        this.id_course = c;
        this.course_name = nc;
        this.number_lessons = nl;


    }

    public int getId_professor() { return id_professor;}

    public String getName_professor() {
        return name_professor;
    }
    public int getId_course(){ return id_course;}
    public String getCourse_name() { return course_name;}
    public int getNumber_lessons() { return number_lessons;}
}
