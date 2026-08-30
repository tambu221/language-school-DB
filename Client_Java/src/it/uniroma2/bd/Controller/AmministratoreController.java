package it.uniroma2.bd.Controller;

import it.uniroma2.bd.Model.DAO.*;
import it.uniroma2.bd.Model.DAO.DAOException.DAOException;
import it.uniroma2.bd.Model.DAO.DAOException.ReportDAO;
import it.uniroma2.bd.Model.Domain.*;
import it.uniroma2.bd.View.AmministratoreView;
import it.uniroma2.bd.Controller.ApplicationController;


import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import static it.uniroma2.bd.Model.Domain.Role.AMMINISTRATORE;

public class AmministratoreController implements ControllerInterface{
    
	

    public void start(){
        ConnectionSingleton manager = ConnectionSingleton.getInstance();
        manager.changeRole(AMMINISTRATORE);


        int choice;
        while(true){

            choice = AmministratoreView.show();

            switch(choice){
                case 1 -> createLevel();
                case 2 -> createCourse();
                case 3 -> insertProfessor();
                case 4 -> professorAssignment();
                case 5 -> createLessons();
                case 6 -> MonthReport();
                case 7 -> Logout(manager);
            }


        }


    }

    public void createLevel(){
         Level l;
         l = AmministratoreView.levelInfo();
         try {
              LevelDAO.insert(l);
         }catch (DAOException e){
             throw new RuntimeException(e);
         }
         System.out.println("Creation level terminated with success\n");

    }

    public void createCourse(){
        Course c;
        c=AmministratoreView.courseInfo();
        try{
             CourseDAO.insertCourse(c);
        }catch(DAOException e){
            throw new RuntimeException(e);
        }

    }

    public void insertProfessor(){
       Professor p;
       
       p=AmministratoreView.professorInfo();
       try{
           InsertProfessorDAO.insert(p);
           
       }catch(DAOException e){
           throw new RuntimeException(e);
       }
     
       System.out.println("Professor insert with success\n");
    }

    public void createLessons(){
        Lesson l;
        l=AmministratoreView.lessonInfo();
        try{
            InsertLessonDAO.insert(l);
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        System.out.println("Lesson insert with succes\n");
    }

    public void professorAssignment(){
        int[] pc_array;
        
        pc_array = AmministratoreView.assignementInfo();
        try{
            ProfessorAssignementDAO.insert(pc_array[0],pc_array[1]);
            
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        
        System.out.println("Professor's assignement terminated with success\n");
    }


    public void MonthReport(){
       ReportList rl;
       rl = ReportDAO.getReport();
       AmministratoreView.showReport(rl);

    }

    public void Logout(ConnectionSingleton c) {
        Connection con;
        con=c.getConnection();
        try {
        con.close();
        c.instance=null;
      }catch(SQLException e) {
    	throw new RuntimeException(e);
    }
        new ApplicationController().start();
    }
}