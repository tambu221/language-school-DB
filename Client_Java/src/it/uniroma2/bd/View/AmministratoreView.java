package it.uniroma2.bd.View;

import it.uniroma2.bd.Model.Domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Objects;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

public class AmministratoreView {

    public static int show(){
        System.out.println("Welcome Administrator\n");
        System.out.println("Operation available:\n");
        System.out.println("1) Creation a new Level\n");
        System.out.println("2) Creation a new Course\n");
        System.out.println("3) New professor inscription\n");
        System.out.println("4) Assignement professor to a course\n");
        System.out.println("5) Insert new lesson\n");
        System.out.println("6) Report Professor activity\n");
        System.out.println("7) Logout\n");
        

        Scanner input = new Scanner(System.in);

        int choice = 0;
        while(true){

            System.out.println("Insert number of your choice\n");
            choice = input.nextInt();
            if(choice >=1 && choice <=7){
                break;
            }
            System.out.println("invalid operation\n");
        }
        
        return choice;


    }

    public static Level levelInfo(){
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String Name ;
        String choice;
        String Book;
        Boolean exam;

        try {
            System.out.println("Insert Level's name:\n");
            Name = buffer.readLine();
            System.out.println("Insert Level's book name:\n");
            Book = buffer.readLine();
            while(true) {
            System.out.println("If level have exam digit Yes else No:\n");
            choice = buffer.readLine();
            if(Objects.equals(choice,"Yes")|Objects.equals(choice, "yes")){
                exam = true;
                
                    
                break;
            }
            if(Objects.equals(choice,"No")|Objects.equals(choice,"no")) {
            	exam = false;
            	
            	break;
            }
            
            System.out.println("Invalid input insert Yes/yes or No/no\n");
         }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
        
        return new Level(Name,Book,exam);
    }

    public static Course courseInfo(){
        String Name_course;
        int year;
        int month;
        int day;
        Scanner sc = new Scanner (System.in);
        System.out.println("Insert name of Course:\n");
        Name_course = sc.nextLine();
        System.out.println("Insert year of start course:\n");
        year = sc.nextInt();
        System.out.println("Insert month of start course\n");
        month = sc.nextInt();
        System.out.println("Insert day of start of course\n");
        day = sc.nextInt();
        return new Course(Name_course,year,month,day);

    }

    public static Professor professorInfo(){
        String name;
        String Nationality;
        String Address;

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert the professor's name\n");
        try {
        name = buffer.readLine();
        System.out.println("Insert professor's Nationality\n");
        Nationality = buffer.readLine();
        System.out.println("Insert professor's address\n");
        Address = buffer.readLine();
        }catch(IOException e) {
        	throw new RuntimeException(e);
        }
        
         return new Professor(name,Nationality,Address);
    }

    public static Lesson lessonInfo(){
        Scanner c = new Scanner(System.in);
        
        int course;
        int professor;
        String start;
        String finish;
        LocalTime inizio = null;
        LocalTime fine = null;
        LocalDate data = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String input_date;
        
        DateTimeFormatter formatter_date=DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Please insert the course's ID");
        course = c.nextInt();
        System.out.println("Please insert the professor's ID");
        professor = c.nextInt();
        c.nextLine();
        while(true) {
        System.out.println("Please insert the hour of start lesson\n");
        start=c.nextLine();
        try{
            inizio=LocalTime.parse(start,formatter);
            break;
        }catch(DateTimeParseException e){
            System.err.println("Invalid format of time insert like 14:30\n");
        }
          
        }
        while(true) {
        System.out.println("Please insert the hour of finish lesson\n");
        finish=c.nextLine();
       
        try{
            fine=LocalTime.parse(finish,formatter);
            break;
        }catch(DateTimeParseException e) {
            System.err.println("Invalid format of time insert like 14:30\n");
            
        }
        }
        while(true) {
        System.out.println("Please insert the lesson date\n");
        input_date = c.nextLine();
        try{
            data=LocalDate.parse(input_date,formatter_date);
            break;
        }catch (DateTimeParseException e){
            System.err.println("Invalid format of date , insert like 25/03/2024");
        }
       } 
        return new Lesson(course,professor,inizio,fine,data);
    }

    public static int[] assignementInfo(){
        int[] assignement = new int[2];
        Scanner c = new Scanner(System.in);
        System.out.println("Insert ID_professor\n");
        assignement[0] = c.nextInt();
        System.out.println("Insert ID_course\n");
        assignement[1]= c.nextInt();
        return assignement;

    }

    public static void showReport(ReportList rl){
        int i=0;
        Report r;
        System.out.println("Matricola | Professore | Id_corso | Livello | Tot_lezioni\n");
        for(i=0; i<rl.getSize();i++){
            r=rl.getItem(i);
            
            System.out.println(r.getId_professor()+":"+r.getName_professor()+":"+r.getId_course()+":"+r.getCourse_name()+":"+r.getNumber_lessons()+"\n");
        }
    }

}
