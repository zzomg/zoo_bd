package server;

import forms.MainForm;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Schema {

    private static void executeScript(String script) {
        Connection dbConn = DBConnection.getConn();
        Statement statement = null;
        try {
            InputStream is = Schema.class.getResourceAsStream(script);
            if (is == null) {
                MainForm.displayError("Файлы тестовых данных не найдены.");
                return;
            }

            Scanner read = new Scanner(is, "UTF-8");
            read.useDelimiter("--#");

            while (read.hasNext()) {
                String str = read.next();
                if (str.trim().isEmpty()) {
                    continue;
                }

                try {
                    statement = dbConn.createStatement();
                    statement.execute(str);
                    dbConn.commit();
                } catch (SQLException e) {
                    //MainForm.displayError(e);
                    dbConn.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    System.err.println("Failed to close statement.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertSampleData() {
        String[] scripts = new String[]{
                "/insert/employee_insert.sql",
                "/insert/animal_insert.sql",
                "/insert/animal_card_insert.sql",
                "/insert/animal_animal_insert.sql",
                "/insert/employee_animal_insert.sql",
                "/insert/employee_animal_card_insert.sql",
                "/insert/feed_type_insert.sql",
                "/insert/user_insert.sql",
                "/insert/feed_insert.sql",
                "/insert/supplier_insert.sql",
                "/insert/supply_insert.sql",
        };
        for (String script : scripts) {
            executeScript(script);
        }
    }

    public static void createTriggers() {
        String script = "/create/triggers_create.sql";
        executeScript(script);
    }

    public static void dropUsers() {
        String[] users = Queries.getUsers();

        Connection dbConn = DBConnection.getConn();
        Statement statement;
        for (String user : users) {
            String str = String.format("drop user %s", user);
            try {
                statement = dbConn.createStatement();
                statement.execute(str);
                dbConn.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void dropSchema() {
        dropUsers();
        String script = "/drop/drop.sql";
        executeScript(script);
    }


    public static void createTables() {
        String[] scripts = new String[]{
                "/create/employee_create.sql",
                "/create/animal_create.sql",
                "/create/animal_card_create.sql",
                "/create/animal_animal_create.sql",
                "/create/employee_animal_create.sql",
                "/create/employee_animal_card_create.sql",
                "/create/feed_create.sql",
                "/create/feed_supplier_create.sql",
                "/create/supply_create.sql",
                "/create/animal_card_feed_create.sql",
                "/create/vet_create.sql",
                "/create/manager_create.sql",
                "/create/cleaner_create.sql",
                "/create/animal_ill_create.sql",
                "/create/user_create.sql",
                "/create/roles_create.sql"
        };
        for (String script : scripts) {
            executeScript(script);
        }
    }
}
