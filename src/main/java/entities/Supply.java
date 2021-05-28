package entities;

import connection.DBConnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Supply implements Entity {
    private final Integer supplyId = 0; // supplyId задается автоматически триггером
    private final String feedSupplierName;
    private final String feedName;
    private final String amount;
    private final Integer price;
    private final String supplyDate;

    public Supply(String feedSupplierName, String feedName,
                  String amount, Integer price, String supplyDate) {
        this.feedSupplierName = feedSupplierName;
        this.feedName = feedName;
        this.amount = amount;
        this.price = price;
        this.supplyDate = supplyDate;
    }

    @Override
    public void updateValues(CachedRowSet tableRowSet) throws SQLException {
        tableRowSet.updateInt("supply_Id", supplyId);
        tableRowSet.updateString("feed_Supplier_Name", feedSupplierName);
        tableRowSet.updateString("feed_Name", feedName);
        tableRowSet.updateString("amount", amount);
        tableRowSet.updateInt("price", price);
        tableRowSet.updateDate("supply_Date", java.sql.Date.valueOf(supplyDate));
    }

    @Override
    public void insertValues() throws SQLException {
        PreparedStatement stmt = DBConnection.getConn()
                .prepareStatement("INSERT INTO supply VALUES(?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, supplyId);
        stmt.setString(2, feedSupplierName);
        stmt.setString(3, feedName);
        stmt.setString(4, amount);
        stmt.setInt(5, price);
        stmt.setDate(6, java.sql.Date.valueOf(supplyDate));

        stmt.addBatch();
        stmt.executeBatch();
    }
}
