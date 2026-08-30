package it.uniroma2.bd.Model.DAO;

import java.sql.*;
import it.uniroma2.bd.Model.Domain.Student;
import it.uniroma2.bd.Model.DAO.DAOException.*;

public class InscritionStudentDAO {

   public static void inscrition(Student S) throws DAOException{
       Connection con = ConnectionSingleton.getInstance().getConnection();
       try {
           CallableStatement cs = con.prepareCall("{ call nuovo_studente(?,?,?)}");
           cs.setString(1, S.getName());
           cs.setString(2, S.getSurname());
           cs.setString(3, S.getNumber_telephone());
           cs.execute();
       }catch(SQLException e){
           throw new DAOException(e.getMessage());
       }


   }

}
