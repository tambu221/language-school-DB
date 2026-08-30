package it.uniroma2.bd.Model.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import it.uniroma2.bd.Model.DAO.DAOException.*;

import it.uniroma2.bd.Model.Domain.StudentStatus;

public class StudentStatusDAO {

    public static void insert(StudentStatus s) throws DAOException {

        Connection con =ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = con.prepareCall("{ call Appello(?,?,?)}");
            cs.setInt(1, s.getId_lesson());
            cs.setInt(2, s.getId_student());
            cs.setBoolean(3, s.get_status());
            cs.execute();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }


    }
}