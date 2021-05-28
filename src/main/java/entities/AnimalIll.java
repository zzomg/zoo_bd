package entities;

import server.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalIll implements Entity {
    private final Integer cardId;
    private final String illness;
    private final String treatment;
    private final String dateStart;
    private final String dateEnd;

    public AnimalIll(Integer cardId, String illness,
                     String treatment, String dateStart,
                     String dateEnd) {
        this.cardId = cardId;
        this.illness = illness;
        this.treatment = treatment;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("card_id", cardId);
        tableRowSet.updateString("illness", illness);
        tableRowSet.updateString("treatment", treatment);
        tableRowSet.updateDate("date_start", java.sql.Date.valueOf(dateStart));
        tableRowSet.updateDate("date_end", java.sql.Date.valueOf(dateEnd));
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO ANIMAL_ILL VALUES(?, ?, ?, ?, ?)");
        stmt.setInt(1, cardId);
        stmt.setString(2, illness);
        stmt.setString(3, treatment);
        stmt.setDate(4, java.sql.Date.valueOf(dateStart));

        java.sql.Date dateEnd = this.dateEnd == null ? null : java.sql.Date.valueOf(this.dateEnd);
        stmt.setDate(5, dateEnd);

        stmt.addBatch();
        stmt.executeBatch();
    }
}
