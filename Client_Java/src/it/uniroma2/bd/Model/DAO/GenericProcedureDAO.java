package it.uniroma2.bd.Model.DAO;
import it.uniroma2.bd.Model.DAO.DAOException.*;

import java.sql.SQLException;

public interface GenericProcedureDAO <P>{

   P execute(Object ... params) throws SQLException,DAOException;
}
