package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manager implements Entity {
    private final Integer empId;
    private final String education;

    public Manager(Integer empId, String education) {
        this.empId = empId;
        this.education = education;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {

    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO MANAGER VALUES(?, ?)");
        stmt.setInt(1, empId);
        stmt.setString(2, education);

        stmt.addBatch();
        stmt.executeBatch();
    }
}