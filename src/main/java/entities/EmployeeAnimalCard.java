package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeAnimalCard implements Entity {
    private final Integer empId;
    private final String firstName;
    private final String lastName;
    private final Integer cardId;
    private final String animalName;
    private final Integer cageNumber;
    private final String dateStart;
    private final String dateEnd;

    public EmployeeAnimalCard(Integer empId, String firstName, String lastName,
                              Integer cardId, String animalName, Integer cageNumber,
                              String dateStart, String dateEnd) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardId = cardId;
        this.animalName = animalName;
        this.cageNumber = cageNumber;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("emp_id", empId);
        tableRowSet.updateString("first_name", firstName);
        tableRowSet.updateString("last_name", lastName);
        tableRowSet.updateInt("card_id", cardId);
        tableRowSet.updateString("animal_name", animalName);
        tableRowSet.updateInt("cage_number", cageNumber);
        tableRowSet.updateDate("date_start", java.sql.Date.valueOf(dateStart));
        tableRowSet.updateDate("date_end", java.sql.Date.valueOf(dateEnd));

    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO EMPLOYEE_ANIMAL_CARD VALUES(?, ?, ?, ?)");
        stmt.setInt(1, empId);
        stmt.setInt(2, cardId);
        stmt.setDate(3, java.sql.Date.valueOf(dateStart));
        stmt.setDate(4, java.sql.Date.valueOf(dateEnd));

        stmt.addBatch();
        stmt.executeBatch();
    }
}