package it.uniroma2.bd.View;
import it.uniroma2.bd.Model.Domain.StudentStatus;

import java.util.Objects;
import java.util.Scanner;
import it.uniroma2.bd.Model.Domain.Lesson;
import it.uniroma2.bd.Model.Domain.LessonList;
import java.util.List;




public class ProfessorView {


    public static int show(){
        Scanner input = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("Welcome Professor\n");
            System.out.println("Operation available:\n");
            System.out.println("1) Register Student's presence/absence\n");
            System.out.println("2) Agenda\n");
            System.out.println("3) Logout\n");
            System.out.println("Insert number of your choice\n");

            choice = input.nextInt();

            if(choice >=1 && choice <=3){
                break;
            }
            System.out.println("operation non defined please insert a number between 1 and 3\n");

        }
        return choice;
    }

    public static StudentStatus statusInfo(){
        Scanner s = new Scanner(System.in);
        Scanner c = new Scanner(System.in);
        int id_l,id_s;
        boolean status;
        String status_string;
        System.out.println("Please enter lesson's ID\n");
        id_l = s.nextInt();
        System.out.println("Please enter student's ID\n");
        id_s = s.nextInt();
        System.out.println("Please enter Yes=presence or NO=absence\n");
        status_string=c.nextLine();
        if(Objects.equals(status_string, "Yes")) {
           status = true ;
        }
        else status = false;
        return new StudentStatus(id_l,id_s,status);
    }

    public static int infoID(){
        Scanner s = new Scanner (System.in);
        int id;
        System.out.println("Please insert your id\n");
        id = s.nextInt();
        return id;

    }


   public static void showAgenda(LessonList ll){
        int i=0;
        Lesson l;
        for(i=0; i < ll.getSize(); i++){
            l=ll.getItem(i);
            System.out.println("Lezione" + (i+1) + ":\n"+"Id_corso="+l.getID_course()+"\n"+"Corso="+l.getNameCourse()+"\n"+"orario_inizio:"+l.getStart()+"\n"+"orario fine:"+l.getFinish()+"\n"+"data:"+l.getDate()+"\n");

        }

   }

}

