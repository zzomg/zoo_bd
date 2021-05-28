package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeAnimal implements Entity {
    private final Integer empId;
    private final String firstName;
    private final String lastName;
    private final String animalName;
    private final String dateStart;
    private final String dateEnd;

    public EmployeeAnimal(Integer empId,
                          String firstName,
                          String lastName,
                          String animalName,
                          String dateStart,
                          String dateEnd) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.animalName = animalName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("emp_id", empId);
        tableRowSet.updateString("first_name", firstName);
        tableRowSet.updateString("last_name", lastName);
        tableRowSet.updateString("animal_name", animalName);
        tableRowSet.updateDate("date_start", java.sql.Date.valueOf(dateStart));
        tableRowSet.updateDate("date_end", java.sql.Date.valueOf(dateEnd));
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO EMPLOYEE_ANIMAL VALUES(?, ?, ?, ?)");
        stmt.setInt(1, empId);
        stmt.setString(2, firstName);
        stmt.setDate(3, java.sql.Date.valueOf(dateStart));
        stmt.setDate(4, java.sql.Date.valueOf(dateEnd));

        stmt.addBatch();
        stmt.executeBatch();
    }
}
