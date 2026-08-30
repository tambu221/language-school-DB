package it.uniroma2.bd.Controller;

import it.uniroma2.bd.Model.DAO.ConnectionSingleton;
import it.uniroma2.bd.Model.DAO.DAOException.DAOException;
import it.uniroma2.bd.Model.DAO.InscritionStudentDAO;
import it.uniroma2.bd.Model.DAO.StudentAssignementDAO;
import it.uniroma2.bd.Model.Domain.Assignement;
import it.uniroma2.bd.Model.Domain.Student;
import it.uniroma2.bd.View.SegretaryView;
import static it.uniroma2.bd.Model.Domain.Role.SEGRETERIA;

import java.sql.Connection;
import java.sql.SQLException;


public class SegretaryController {

    public void start(){
        ConnectionSingleton manager = ConnectionSingleton.getInstance();
        manager.changeRole(SEGRETERIA);

        int choice;
         while(true){
             choice = SegretaryView.show();
             switch(choice) {
                 case 1 -> inscriptionStudent();
                 case 2 -> assignementStudent();
                 case 3 -> Logout(manager);
             }
             
         }
     }

    public void inscriptionStudent() {
        Student s;
        s = SegretaryView.studentInfo();

        try {
            InscritionStudentDAO.inscrition(s);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("Student's insert termineted with success\n");
    }

    public void assignementStudent(){
        Assignement info;
        info = SegretaryView.assignementInfo();
        StudentAssignementDAO.assignement(info);
        System.out.println("Assignement terminated with success\n");

    }

    public void Logout(ConnectionSingleton c) {
    	Connection con;
    	con=c.getConnection();
    	try {
    		con.close();
    		
    	}catch(SQLException e ) {
    		throw new RuntimeException(e);
    	}
    
        c.instance=null;
        new ApplicationController().start();
    }

}
