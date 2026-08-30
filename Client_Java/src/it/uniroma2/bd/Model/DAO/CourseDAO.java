package it.uniroma2.bd.Model.DAO;
import it.uniroma2.bd.Model.Domain.Course;

import java.lang.RuntimeException;
import java.sql.*;
import java.sql.CallableStatement;
import java.time.LocalDate;
import it.uniroma2.bd.Model.DAO.DAOException.*;


public class CourseDAO {

    public static void insertCourse(Course c) throws DAOException{
        LocalDate data;
        try {
            Connection con =ConnectionSingleton.getInstance().getConnection();
            CallableStatement cs = con.prepareCall("{call Crea_corso(?,?)}");
            cs.setString(1, c.getName_course());
            data = LocalDate.of(c.getYear(), c.getMonth(), c.getDay());
            cs.setObject(2, data);
            cs.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}