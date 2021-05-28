package view.tables;

import com.sun.rowset.CachedRowSetImpl;
import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableUtils {
    public static CachedRowSet getContentsOfTable(String tableName) throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);

        crs.setCommand("select * from " + tableName);
        crs.execute(DBConnection.getConn());

        return crs;
    }

    public static CachedRowSet getContentsOfTableWithCondition(String tableName,
                                                               String whereClause)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);

        crs.setCommand("select * from " + tableName + " " + whereClause);
        crs.execute(DBConnection.getConn());

        return crs;
    }

    public static CachedRowSet getCustomContentsOfTable(String query)
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);

        crs.setCommand(query);
        crs.execute(DBConnection.getConn());

        return crs;
    }

}
