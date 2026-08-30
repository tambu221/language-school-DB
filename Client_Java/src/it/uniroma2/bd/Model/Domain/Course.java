package it.uniroma2.bd.Model.Domain;

public class Course {
   private final String name_course;
   private final int year;
   private final int month;
   private final int day;

   public Course(String nc,int y,int m,int d){
      this.name_course = nc;
      this.year = y;
      this.month = m;
      this.day = d;
   }

   public String getName_course(){ return name_course;}

   public int getYear(){ return year;}

   public int getMonth(){ return month; }

   public int getDay(){ return day;}
}
