package forms.tables;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import server.DBConnection;
import entities.Entity;
import model.EntityTableProperties;
import forms.FrameLocation;
import forms.MainForm;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TableViewForm extends JPanel implements RowSetListener {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addRowButton;
    private JPanel newEntityPanel;
    private JButton revertButton;
    private JLabel rowCountLabel;
    private JButton filterButton;
    private ImplTableModel model;
    private final String tableName;
    private Integer[] nonEditableColumns;

    // Без панели ввода новой сущности
    public TableViewForm(String tableName) {
        try {
            this.newEntityPanel = EntityTableProperties.tablePropertiesMap.get(tableName).getEntityPanel();
            ((NewEntityPanel) this.newEntityPanel).initFilterForm(this);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        this.tableName = EntityTableProperties.tablePropertiesMap.get(tableName).getEntityName();

        $$$setupUI$$$();

        initTable();
        initButtons();
        removeButtons();
        newEntityPanel.setVisible(false);
        setTableSize();
        this.add(mainPanel);
        this.setVisible(true);
    }


    private void setTableSize() {
        Dimension size = new Dimension(table.getMinimumSize().width,
                table.getMinimumSize().height * 2);
        scrollPane.setMinimumSize(size);
        scrollPane.setPreferredSize(size);
        table.setMinimumSize(size);
        table.setSize(size);
        table.setPreferredScrollableViewportSize(size);
    }

    private void removeButtons() {
        addRowButton.setVisible(false);
        deleteButton.setVisible(false);
        revertButton.setVisible(false);
    }


    public TableViewForm(EntityTableProperties properties) {
        try {
            this.newEntityPanel = properties.getEntityPanel();
            ((NewEntityPanel) this.newEntityPanel).initFilterForm(this);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        this.tableName = properties.getEntityName();
        this.nonEditableColumns = properties.getNonEditableColumns();

        $$$setupUI$$$();

        initTable();
        initButtons();
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void initButtons() {
        addRowButton.addActionListener(e -> {
            if (model == null || newEntityPanel == null) {
                return;
            }
            try {
                Entity entity = ((NewEntityPanel) newEntityPanel).getEntity();
                model.insertRow(entity);
            } catch (IllegalArgumentException exception) {
                MainForm.displayError(exception);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            table.revalidate();
        });

        updateButton.addActionListener(e -> {
            if (model == null) {
                return;
            }
            try {
                model.tableRowSet.acceptChanges(DBConnection.getConn());
                model.tableRowSet.commit();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                MainForm.displayError(sqle);
            } finally {
                initTable();
            }
        });

        deleteButton.addActionListener(e -> {
            if (model == null) {
                return;
            }
            try {
                int row;
                if ((row = table.getSelectedRow()) != -1) {
                    model.tableRowSet.absolute(row + 1);
                    model.tableRowSet.deleteRow();
                    table.repaint();
                    table.clearSelection();
                }
            } catch (SQLException throwables) {
                MainForm.displayError(throwables);
            }
        });

        revertButton.addActionListener(e -> {
            int input = MainForm.displayYesNoPane("Отменить изменения?", "Отмена изменений");
            if (input == 0) {
                initTable();
            }
        });

        filterButton.addActionListener(e -> {
            JFrame filterForm = ((NewEntityPanel) this.newEntityPanel).getFilterForm();
            if (filterForm == null) {
                MainForm.displaySuccess("Фильтры недоступны");
                return;
            }
            filterForm.setVisible(true);
            FrameLocation.centerFrameLocation(filterForm);
        });
    }

    public void filterTable(String query) {
        try {
            CachedRowSet cachedRowSet = TableUtils.getContentsOfTableWithCondition(tableName, query);
            model = new ImplTableModel(cachedRowSet, nonEditableColumns);
            model.addEventHandlersToRowSet(this);
            table.getTableHeader().setReorderingAllowed(false);
            table.setModel(model);
            rowCountLabel.setText("Значений: " + model.getRowCount());
        } catch (SQLException e) {
            initTable();
            MainForm.displayError(e);
        }
    }

    private void initTable() {
        try {
            CachedRowSet cachedRowSet = TableUtils.getContentsOfTable(tableName);
            model = new ImplTableModel(cachedRowSet, nonEditableColumns);
            model.addEventHandlersToRowSet(this);
            table.getTableHeader().setReorderingAllowed(false);
            table.setModel(model);
            rowCountLabel.setText("Значений: " + model.getRowCount());
        } catch (SQLException e) {
            MainForm.displayError(e);
        }
    }


    @Override
    public void rowSetChanged(RowSetEvent event) {

    }

    @Override
    public void rowChanged(RowSetEvent event) {
        if (model == null) {
            return;
        }
        CachedRowSet currentRowSet = this.model.tableRowSet;
        try {
            currentRowSet.moveToCurrentRow();
            model.setTableRowSet(model.getTableRowSet());
            table.setModel(model);
            rowCountLabel.setText("Значений: " + model.getRowCount());
        } catch (SQLException ex) {
            MainForm.displayError(ex);
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMinimumSize(new Dimension(900, 800));
        scrollPane = new JScrollPane();
        mainPanel.add(scrollPane, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(900, 250), null, null, 0, false));
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(4);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        table.setDragEnabled(false);
        table.setFillsViewportHeight(true);
        table.setMinimumSize(new Dimension(900, 250));
        table.setPreferredScrollableViewportSize(new Dimension(-1, -1));
        table.setRowHeight(20);
        table.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE);
        scrollPane.setViewportView(table);
        mainPanel.add(newEntityPanel, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 250), null, null, 0, false));
        addRowButton = new JButton();
        addRowButton.setText("Добавить запись");
        mainPanel.add(addRowButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Удалить выбранную запись");
        mainPanel.add(deleteButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        revertButton = new JButton();
        revertButton.setText("Отменить изменения");
        mainPanel.add(revertButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rowCountLabel = new JLabel();
        rowCountLabel.setText("");
        mainPanel.add(rowCountLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        filterButton = new JButton();
        filterButton.setText("Фильтры");
        mainPanel.add(filterButton, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Обновить данные");
        mainPanel.add(updateButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
    }
}
