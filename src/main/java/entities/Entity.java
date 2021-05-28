package entities;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public interface Entity {
    void updateValues(CachedRowSet tableRowSet) throws SQLException;
    void insertValues() throws SQLException;
}
