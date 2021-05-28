package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Feed implements Entity {
    private final String feedName;
    private final String feedType;

    public Feed(String feedName, String feedType) {
        this.feedName = feedName;
        this.feedType = feedType;
    }


    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateString("feed_name", feedName);
        tableRowSet.updateString("feed_type", feedType);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO FEED VALUES(?, ?)");
        stmt.setString(1, feedName);
        stmt.setString(2, feedType);

        stmt.addBatch();
        stmt.executeBatch();
    }
}