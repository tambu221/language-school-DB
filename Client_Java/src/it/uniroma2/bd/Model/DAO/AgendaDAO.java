package it.uniroma2.bd.Model.DAO;
import it.uniroma2.bd.Model.DAO.DAOException.DAOException;
import it.uniroma2.bd.Model.Domain.Lesson;
import it.uniroma2.bd.Model.Domain.LessonList;

import java.sql.ResultSet;
import java.util.List;
import java.sql.*;

public class AgendaDAO {

    public static LessonList getAgenda(int Id_professor){
        LessonList lessonList = new LessonList();
        Connection con =ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call Agenda(?)}");
            cs.setInt(1,Id_professor);
            boolean status = cs.execute();
            if(status) {
                ResultSet rs;
                rs = cs.getResultSet();
                while(rs.next()){
                      lessonList.addLesson(new Lesson(rs.getInt(2),rs.getInt(1),rs.getTime(5).toLocalTime(),rs.getTime(6).toLocalTime(),rs.getDate(7).toLocalDate(),rs.getString(3)));

                }
            }

        }catch(DAOException | SQLException e){
            throw new RuntimeException(e);
        }
        return lessonList;

    }


}
