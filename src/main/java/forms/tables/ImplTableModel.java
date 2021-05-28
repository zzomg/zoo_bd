package forms.tables;

import entities.Entity;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImplTableModel implements TableModel {
    CachedRowSet tableRowSet; // The ResultSet to interpret
    ResultSetMetaData metadata; // Additional information about the results
    int numcols, numrows; // How many rows and columns in the table
    private final ArrayList<Integer> nonEditableColumns = new ArrayList<>();

    public ImplTableModel(CachedRowSet rowSetArg, Integer[] nonEditableColumns) throws SQLException {
        if (nonEditableColumns != null) {
            this.nonEditableColumns.addAll(Arrays.asList(nonEditableColumns));
        }
        this.tableRowSet = rowSetArg;
        this.metadata = this.tableRowSet.getMetaData();
        numcols = metadata.getColumnCount();

        // Retrieve the number of rows.
        this.tableRowSet.beforeFirst();
        this.numrows = 0;
        while (this.tableRowSet.next()) {
            this.numrows++;
        }
        this.tableRowSet.beforeFirst();
    }

    public void insertRow(Entity entity) throws SQLException {
        tableRowSet.moveToInsertRow();
        entity.updateValues(tableRowSet);
        tableRowSet.insertRow();
        tableRowSet.moveToCurrentRow();
    }

    public void setTableRowSet(CachedRowSet rowSetArg) throws SQLException {
        this.tableRowSet = rowSetArg;
        this.metadata = this.tableRowSet.getMetaData();
        numcols = metadata.getColumnCount();

        // Retrieve the number of rows.
        this.tableRowSet.beforeFirst();
        this.numrows = 0;
        while (this.tableRowSet.next()) {
            this.numrows++;
        }
        this.tableRowSet.beforeFirst();
    }

    public CachedRowSet getTableRowSet() {
        return tableRowSet;
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.tableRowSet.addRowSetListener(listener);
    }

    public void close() {
        try {
            tableRowSet.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void finalize() {
        close();
    }

    @Override
    public int getRowCount() {
        return numrows;
    }

    @Override
    public int getColumnCount() {
        return numcols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return this.metadata.getColumnLabel(columnIndex + 1);
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !nonEditableColumns.contains(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            this.tableRowSet.absolute(rowIndex + 1);
            Object o = this.tableRowSet.getObject(columnIndex + 1);
            if (o == null)
                return null;
            else
                return o.toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            tableRowSet.absolute(rowIndex + 1);
            tableRowSet.updateObject(columnIndex + 1, aValue);
            tableRowSet.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
