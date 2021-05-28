package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalCardFeed implements Entity {
    private final Integer cardId;
    private final String feedName;
    private final String feedAmount;
    private final String feedTime;

    public AnimalCardFeed(Integer cardId, String feedName,
                          String feedAmount, String feedTime) {
        this.cardId = cardId;
        this.feedName = feedName;
        this.feedAmount = feedAmount;
        this.feedTime = feedTime;
    }


    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {

    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ANIMAL_CARD_FEED VALUES(?, ?, ?,?)");
        stmt.setInt(1, cardId);
        stmt.setString(2, feedName);
        stmt.setString(3, feedAmount);
        stmt.setString(4, feedTime);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
