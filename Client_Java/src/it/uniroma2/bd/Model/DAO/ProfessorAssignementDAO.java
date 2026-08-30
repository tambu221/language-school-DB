package it.uniroma2.bd.Model.DAO;

import it.uniroma2.bd.Model.DAO.DAOException.*;
import java.sql.CallableStatement;
import java.sql.*;

public class ProfessorAssignementDAO {

    public static void insert(int p,int c) throws DAOException  {
        Connection cn=ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = cn.prepareCall("{ call Assegnazione_insegnante(?,?)}");
            cs.setInt(1, p);
            cs.setInt(2, c);
            cs.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }

       
    }

}
