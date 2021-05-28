package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedSupplier implements Entity {
    private final String feedSupplierName;


    public FeedSupplier(String feedSupplierName) {
        this.feedSupplierName = feedSupplierName;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateString("feed_supplier_name", feedSupplierName);
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO FEED_SUPPLIER VALUES(?)");
        stmt.setString(1, feedSupplierName);

        stmt.addBatch();
        stmt.executeBatch();
    }
}