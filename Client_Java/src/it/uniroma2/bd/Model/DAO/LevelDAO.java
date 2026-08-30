package it.uniroma2.bd.Model.DAO;
import it.uniroma2.bd.Model.Domain.Level;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import it.uniroma2.bd.Model.DAO.ConnectionSingleton;
import it.uniroma2.bd.Model.DAO.DAOException.*;

public class LevelDAO {


    public static void insert(Level l) throws DAOException{
        ConnectionSingleton manager = ConnectionSingleton.getInstance();
        Connection con = manager.getConnection();
        try {
        CallableStatement cs = con.prepareCall("{call nuovo_livello(?,?,?)}");
            cs.setString(1, l.getNameLevel());
            cs.setString(2, l.getBookName());
            cs.setBoolean(3, l.getExam());
            cs.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }
    }

}
