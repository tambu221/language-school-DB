package it.uniroma2.bd.Model.DAO;

import it.uniroma2.bd.Model.Domain.Lesson;
import java.sql.*;
import it.uniroma2.bd.Model.DAO.DAOException.*;

public class InsertLessonDAO {

    public static void insert(Lesson l) throws DAOException {
        Connection con = ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = con.prepareCall("{ call Nuova_lezione(?,?,?,?,?)}");
            cs.setInt(1, l.getID_course());
            cs.setInt(2, l.getID_professor());
            cs.setObject(3, l.getStart());
            cs.setObject(4, l.getFinish());
            cs.setObject(5, l.getDate());
            cs.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}