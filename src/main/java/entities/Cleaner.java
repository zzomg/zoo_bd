package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cleaner implements Entity {
    private final Integer empId;
    private final String cleanerType;

    public Cleaner(Integer empId, String cleanerType) {
        this.empId = empId;
        this.cleanerType = cleanerType;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {

    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO CLEANER VALUES(?, ?)");
        stmt.setInt(1, empId);
        stmt.setString(2, cleanerType);

        stmt.addBatch();
        stmt.executeBatch();
    }
}