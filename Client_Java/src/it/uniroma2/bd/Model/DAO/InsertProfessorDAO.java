package it.uniroma2.bd.Model.DAO;
import java.sql.*;
import it.uniroma2.bd.Model.Domain.Professor;
import it.uniroma2.bd.Model.DAO.DAOException.*;

public class InsertProfessorDAO {

    public static void insert(Professor p) throws DAOException{

        try{
            Connection con =ConnectionSingleton.getInstance().getConnection();
            CallableStatement cs = con.prepareCall("{call Nuovo_insegnante(?,?,?)}");
            cs.setString(1,p.getName());
            cs.setString(2,p.getNationality());
            cs.setString(3,p.getAddress());
            
            cs.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }


    }
}
