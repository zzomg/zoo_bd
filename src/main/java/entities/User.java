package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User implements Entity{
    String username;
    String userRole;

    public User(String username, String userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateString("username", username);
        tableRowSet.updateString("user_role", userRole);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ZOO_USER VALUES(?, ?)");
        stmt.setString(1, username);
        stmt.setString(2, userRole);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
