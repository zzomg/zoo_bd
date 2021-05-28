package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalCard implements Entity {
    private final Integer cardId = 0; // cardId задается автоматически триггером
    private final String animalName;
    private final Integer cageNumber;
    private final Integer weight;
    private final Integer height;
    private final String sex;
    private final String birthDate;
    private final String admissionDate;
    private final Integer ill;
    private final Integer isolated;

    public AnimalCard(String animalName,
                      Integer cageNumber,
                      Integer weight,
                      Integer height,
                      String sex,
                      String birthDate,
                      String admissionDate,
                      Integer ill,
                      Integer isolated) {
        this.animalName = animalName;
        this.cageNumber = cageNumber;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.ill = ill;
        this.isolated = isolated;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("card_id", cardId);
        tableRowSet.updateString("animal_name", animalName);
        tableRowSet.updateInt("cage_number", cageNumber);
        tableRowSet.updateInt("weight", weight);
        tableRowSet.updateInt("height", height);
        tableRowSet.updateString("sex", sex);
        tableRowSet.updateDate("birth_date", java.sql.Date.valueOf(birthDate));
        tableRowSet.updateDate("admission_date", java.sql.Date.valueOf(admissionDate));
        tableRowSet.updateInt("ill", ill);
        tableRowSet.updateInt("isolated", isolated);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ANIMAL_CARD VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, cardId);
        stmt.setString(2, animalName);
        stmt.setInt(3, cageNumber);
        stmt.setInt(4, weight);
        stmt.setInt(5, height);
        stmt.setString(6, sex);
        stmt.setDate(7, java.sql.Date.valueOf(birthDate));
        stmt.setDate(8, java.sql.Date.valueOf(admissionDate));
        stmt.setInt(9, ill);
        stmt.setInt(10, isolated);
        stmt.setInt(11, 0);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
