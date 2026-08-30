package it.uniroma2.bd.View;

import java.io.IOException;
import java.lang.System;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import it.uniroma2.bd.Model.Domain.Assignement;
import it.uniroma2.bd.Model.Domain.Student;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SegretaryView {

    public static int show() {
        System.out.println("Welcome Segreatary member\n");
        System.out.println("Operation available:\n");
        System.out.println("1) New Student Inscriction\n");
        System.out.println("2) New Student assignement\n");
        System.out.println("3) Logout\n");
        System.out.println("Insert number of your choice\n");

        int choice;
        Scanner input = new Scanner (System.in);

        while(true){
            choice = input.nextInt();
            if(choice >=1 && choice <=3){
                break;
            }
            System.out.println("Invalid operation please insert a number between 1 and 3\n");
        }

       return choice;
    }

    public static Student studentInfo(){
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String Name,Surname,Telephone;
        try {
            System.out.println("Insert the Student's name\n");
            Name = buffer.readLine();
            System.out.println("Insert Student's surname\n");
            Surname = buffer.readLine();
            System.out.println("Insert Student's telephone\n");
            Telephone = buffer.readLine();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return new Student(Name,Surname,Telephone);


    }

   public static Assignement assignementInfo(){
        int student,course;
        Scanner c = new Scanner (System.in);
        Scanner h= new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Insert the Student's ID\n");
        student=c.nextInt();
        System.out.println("Insert Course's ID\n");
        course = c.nextInt();
        LocalDate data;
        System.out.println("insert date of inscription\n");
        String input=h.nextLine();
        
        data=LocalDate.parse(input,formatter);

        return new Assignement(student,course,data);
    }

}
