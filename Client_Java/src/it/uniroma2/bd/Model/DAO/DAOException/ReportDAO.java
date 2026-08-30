package it.uniroma2.bd.Model.DAO.DAOException;

import it.uniroma2.bd.Model.DAO.ConnectionSingleton;
import it.uniroma2.bd.Model.Domain.Report;
import it.uniroma2.bd.Model.Domain.ReportList;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ReportDAO {

    public static ReportList getReport() {
        ReportList rl = new ReportList();

        Connection con = ConnectionSingleton.getInstance().getConnection();
        try {
            CallableStatement cs = con.prepareCall("{ call Report_mensile()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();

            while (rs.next()) {
                rl.addReport(new Report(rs.getInt(2), rs.getString(1), rs.getInt(3), rs.getString(4), rs.getInt(5)));

            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return rl;
    }
}