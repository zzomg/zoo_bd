package view.tables;

import entities.Entity;

import javax.swing.*;
import java.sql.SQLException;

public interface NewEntityPanel {
    Entity getEntity() throws SQLException;

    void insertEntity() throws SQLException;

    void initFilterForm(TableViewForm tableView);

    JFrame getFilterForm();
}
