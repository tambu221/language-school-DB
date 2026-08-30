package it.uniroma2.bd.Model.DAO;

import it.uniroma2.bd.Model.Domain.Assignement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import it.uniroma2.bd.Model.DAO.DAOException.*;

public class StudentAssignementDAO {

    public static void assignement(Assignement A) throws DAOException {

        Connection con = ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call nuova_inscrizione(?,?,?)}");
            cs.setInt(1, A.getId_student());
            cs.setInt(2, A.getId_course());
            cs.setObject(3, A.getDate());
            cs.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }
}
