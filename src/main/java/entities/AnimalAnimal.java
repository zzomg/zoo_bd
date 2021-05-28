package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalAnimal implements Entity {
    private final String animalNameFirst;
    private final String animalNameSecond;

    public AnimalAnimal(String animalNameFirst, String animalNameSecond) {
        this.animalNameFirst = animalNameFirst;
        this.animalNameSecond = animalNameSecond;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateString("animal_name_first", animalNameFirst);
        tableRowSet.updateString("animal_name_second", animalNameSecond);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ANIMAL_ANIMAL VALUES(?, ?)");
        stmt.setString(1, animalNameFirst);
        stmt.setString(2, animalNameSecond);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
