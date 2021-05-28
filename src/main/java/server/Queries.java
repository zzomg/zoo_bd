package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    public static String[] getAnimalNames() {
        List<String> animalNames = new ArrayList<>();
        try {
            String sql = "select ANIMAL_NAME from ANIMAL ";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                animalNames.add(rs.getString("animal_name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalNames.toArray(new String[0]);
    }

    public static String[] getEmployees() {
        List<String> employees = new ArrayList<>();
        try {
            String sql = "select emp_id, first_name, last_name from EMPLOYEE";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                employees.add(rs.getString("emp_id") + " "
                        + rs.getString("first_name") + " "
                        + rs.getString("last_name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees.toArray(new String[0]);
    }

    public static String[] getAnimalCards() {
        List<String> animalCards = new ArrayList<>();
        try {
            String sql = "select CARD_ID, ANIMAL_NAME, CAGE_NUMBER from ANIMAL_CARD";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                animalCards.add(rs.getString("CARD_ID") + " "
                        + rs.getString("ANIMAL_NAME") + " "
                        + rs.getString("CAGE_NUMBER"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalCards.toArray(new String[0]);
    }

    public static String[] getIllAnimals() {
        List<String> illAnimals = new ArrayList<>();
        try {
            String sql = "select CARD_ID, ANIMAL_NAME, ILLNESS, CAGE_NUMBER from ANIMAL_ILL " +
                    " join ANIMAL_CARD using (card_id) where DATE_END is null ";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                illAnimals.add(rs.getString("CARD_ID") + ", "
                        + rs.getString("ANIMAL_NAME") + ", "
                        + rs.getString("ILLNESS") + ", "
                        + rs.getString("CAGE_NUMBER"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return illAnimals.toArray(new String[0]);
    }

    public static String getTreatment(String cardId, String illness) {
        String treatment = null;
        try {
            String sql = String.format("select treatment from animal_ill" +
                            " where card_id=%s and illness like '%s'",
                    cardId, illness);
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                treatment = rs.getString("treatment");
            }
            rs.close();
        } catch (SQLException ignored) {
            return null;
        }
        return treatment;
    }

    public static String[] getCageNumbers() {
        List<String> animalCards = new ArrayList<>();
        try {
            String sql = "select CAGE_NUMBER from ANIMAL_CARD";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                animalCards.add(rs.getString("CAGE_NUMBER"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalCards.toArray(new String[0]);
    }

    public static int getNextId() throws SQLException {
        String sql = "select employee_sequence.nextval from dual";
        Statement stmt = DBConnection.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt(1);
            rs.close();
            return id;
        }
        return -1;
    }

    public static String[] getFeedNames() {
        List<String> feedNames = new ArrayList<>();
        try {
            String sql = "select feed_name from FEED";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                feedNames.add(rs.getString("feed_name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedNames.toArray(new String[0]);
    }

    public static String[] getSupplierNames() {
        List<String> supplierNames = new ArrayList<>();
        try {
            String sql = "select FEED_SUPPLIER_NAME from FEED_SUPPLIER";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                supplierNames.add(rs.getString("FEED_SUPPLIER_NAME"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierNames.toArray(new String[0]);

    }

    public static String[] getNeighborsAnimalNames(int cageNumber) {
        List<String> neighbors = new ArrayList<>();
        try {
            String sql = "select animal_name from ANIMAL_CARD " +
                    "where CAGE_NUMBER between (? - 1) and (? + 1)";
            PreparedStatement stmt = DBConnection.getConn().prepareStatement(sql);
            stmt.setInt(1, cageNumber);
            stmt.setInt(2, cageNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String animalName = rs.getString("animal_name");
                if (!neighbors.contains(animalName)) {
                    neighbors.add(animalName);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return neighbors.toArray(new String[0]);
    }

    public static String[] getNonCompatibleAnimals(String animalName) {
        List<String> nonCompAnimals = new ArrayList<>();
        try {
            String sql = "select animal_name " +
                    "from ANIMAL " +
                    "where ANIMAL_NAME not in " +
                    "( select animal_name " +
                    "from ANIMAL_ANIMAL join ANIMAL " +
                    "on (ANIMAL_NAME_FIRST = ANIMAL_NAME " +
                    "or ANIMAL_NAME_SECOND = ANIMAL_NAME) " +
                    "where ANIMAL_NAME_FIRST = ? or ANIMAL_NAME_SECOND = ?)";
            PreparedStatement stmt = DBConnection.getConn().prepareStatement(sql);
            stmt.setString(1, animalName);
            stmt.setString(2, animalName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nonCompAnimal = rs.getString("animal_name");
                if (!animalName.equalsIgnoreCase(nonCompAnimal)) {
                    nonCompAnimals.add(nonCompAnimal);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nonCompAnimals.toArray(new String[0]);
    }

    public static String getUserRole(String username) {
        String role = null;
        try {
            String sql = String.format("select user_role from ZOO_USER where USERNAME='%s'",
                    username);
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                role = rs.getString("user_role");
            }
            rs.close();
        } catch (SQLException ignored) {
            return null;
        }
        return role;
    }

    public static String[] getUsers() {
        List<String> users = new ArrayList<>();
        try {
            String sql = "select username from ZOO_USER";
            Statement stmt = DBConnection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.add(rs.getString("username"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users.toArray(new String[0]);
    }

}
