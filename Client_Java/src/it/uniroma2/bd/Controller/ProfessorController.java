package it.uniroma2.bd.Controller;
import it.uniroma2.bd.Model.DAO.ConnectionSingleton;
import it.uniroma2.bd.Model.DAO.DAOException.DAOException;
import it.uniroma2.bd.Model.DAO.StudentStatusDAO;
import it.uniroma2.bd.Model.Domain.LessonList;
import it.uniroma2.bd.Model.Domain.StudentStatus;
import it.uniroma2.bd.View.ProfessorView;
import it.uniroma2.bd.Model.DAO.AgendaDAO;
import it.uniroma2.bd.Model.Domain.Lesson;
import static it.uniroma2.bd.Model.Domain.Role.INSEGNANTE;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class ProfessorController {

    public static void start(){
        ConnectionSingleton manager = ConnectionSingleton.getInstance();
        manager.changeRole(INSEGNANTE);


        int choice;
        while(true) {
        choice = ProfessorView.show();

        switch(choice){
            case 1 ->StudentStatus();
            case 2 ->agenda();
            case 3 -> Logout(manager);
        }
       
        }

    }


    public static void StudentStatus(){
        StudentStatus s=null;
       
        boolean status=false;
        while(!status) {
        	s=ProfessorView.statusInfo();
        	try{
            StudentStatusDAO.insert(s);
            status=true;
        }catch(DAOException e){
            System.out.println("Error Message:"+e.getMessage());
            
        }
       
    }
        System.out.println("Register status of student"+s.getId_student()+"and lesson"+s.getId_lesson()+"terminated with success\n");
        }

   public static void agenda(){

        LessonList agenda;
        int ID_professor;
        ID_professor = ProfessorView.infoID();
        agenda = AgendaDAO.getAgenda(ID_professor);
        ProfessorView.showAgenda(agenda);
   }



   public static void Logout(ConnectionSingleton c) {
	   Connection con;
       con=c.getConnection();
	   
	   try {
		  con.close();
		   
	   }catch(SQLException e) {
		   throw new RuntimeException(e);
	   }
       c.instance=null; 
       
       new ApplicationController().start();
   }

}


