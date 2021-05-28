package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vet implements Entity {
    private final Integer empId;
    private final String animalGroup;
    private final String education;

    public Vet(Integer empId, String animalGroup, String education) {
        this.empId = empId;
        this.animalGroup = animalGroup;
        this.education = education;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {

    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO vet VALUES(?, ?, ?)");
        stmt.setInt(1, empId);
        stmt.setString(2, animalGroup);
        stmt.setString(3, education);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
