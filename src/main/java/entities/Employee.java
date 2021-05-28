package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee implements Entity {
    private final Integer empId = 0; // empId задается автоматически триггером
    private final String firstName;
    private final String lastName;
    private final String hireDate;
    private final String sex;
    private final Integer age;
    private final Integer salary;
    private final String jobType;
    private final Integer cageAllow = 0; // cageAllow задается автоматически триггером


    public Employee(String firstName,
                    String lastName,
                    String hireDate,
                    String sex,
                    Integer age,
                    Integer salary,
                    String jobType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
        this.jobType = jobType;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("emp_id", empId);
        tableRowSet.updateString("first_name", firstName);
        tableRowSet.updateString("last_name", lastName);
        tableRowSet.updateDate("hire_date", java.sql.Date.valueOf(hireDate));
        tableRowSet.updateString("sex", sex);
        tableRowSet.updateInt("age", age);
        tableRowSet.updateInt("salary", salary);
        tableRowSet.updateString("job_type", jobType);
        tableRowSet.updateInt("cage_allow", cageAllow);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, empId);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        stmt.setDate(4, java.sql.Date.valueOf(hireDate));
        stmt.setString(5, sex);
        stmt.setInt(6, age);
        stmt.setInt(7, salary);
        stmt.setString(8, jobType);
        stmt.setInt(9, cageAllow);

        stmt.addBatch();
        stmt.executeBatch();

    }
}

