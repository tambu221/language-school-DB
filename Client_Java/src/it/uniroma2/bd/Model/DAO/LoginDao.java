package it.uniroma2.bd.Model.DAO;

import it.uniroma2.bd.Model.Domain.Credentials;
import it.uniroma2.bd.Model.Domain.Role;
import it.uniroma2.bd.exception.*;

import java.sql.*;
import java.lang.Class;

public class LoginDao {
    private static String username;
    private static String password;
    private static Role role;

   public static Credentials getRoleDAO(Credentials cred) throws DAOException{
       Connection conn = null;
       CallableStatement cs = null;
       ResultSet result;
       int role;
       try {


           conn =ConnectionSingleton.getInstance().getConnection();

           cs = conn.prepareCall("{call login(?,?,?)}");

           cs.setString(1, cred.getUsername());
           cs.setString(2, cred.getPassword());
           cs.registerOutParameter(3, Types.NUMERIC);
           
           cs.execute();
           role = cs.getInt(3);
           
           
       }catch(SQLException e){
           throw new DAOException("login error" + e.getMessage());
       }

      return new Credentials(cred.getUsername(),cred.getPassword(),Role.fromInt(role));
   }

}
