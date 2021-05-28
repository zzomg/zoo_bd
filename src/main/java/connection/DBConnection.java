package connection;

import view.MainForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

public class DBConnection {
    private static Connection conn;

    public static Connection getConn() {
        return conn;
    }

    public static void connect(Map<MainForm.Settings, String> settingsMap) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:oracle:thin:@" + settingsMap.get(MainForm.Settings.ADDRESS);
        Properties props = new Properties();
        try {
            props.setProperty("user", settingsMap.get(MainForm.Settings.LOGIN));
            props.setProperty("password", settingsMap.get(MainForm.Settings.PASSWORD));
        } catch (NullPointerException e) {
            MainForm.displayError("Неправильно введены данные!");
            return;
        }

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        DriverManager.setLoginTimeout(5);
        try {
            conn = DriverManager.getConnection(url, props);
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            String alterSession = String.format("alter session set current_schema = \"%s\"",
                    settingsMap.get(MainForm.Settings.SCHEMA).toUpperCase());
            statement.execute(alterSession);
            conn.commit();
        } catch (SQLException e) {
            MainForm.displayError(e);
        }
    }


    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
