package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Animal implements Entity {
    private final String animalName;
    private final String animalType;
    private final Integer reqWarmCage;

    public Animal(String animalName, String animalType, Integer reqWarmCage) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.reqWarmCage = reqWarmCage;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateString("animal_name", animalName);
        tableRowSet.updateString("animal_type", animalType);
        tableRowSet.updateInt("req_warm_cage", reqWarmCage);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ANIMAL VALUES(?, ?, ?)");
        stmt.setString(1, animalName);
        stmt.setString(2, animalType);
        stmt.setInt(3, reqWarmCage);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
