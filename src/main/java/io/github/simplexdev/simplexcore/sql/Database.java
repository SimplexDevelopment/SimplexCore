package io.github.simplexdev.simplexcore.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    public static void createTable(String table, String columns) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "
            + table + " (" + columns + ");");
            ps.executeUpdate();
        } catch(SQLException ex) {
            // TODO
        }
    }

    public static void insertData(String columns, String values, String table) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("INSERT INTO "
            + table + "(" + columns + ") VALUES (" + values + ");");
            ps.executeUpdate();
        } catch(SQLException ex) {
            // TODO
        }
    }

    public static void deleteData(String table, String column, Object value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("DELETE FROM " + table + " WHERE " + column + "=?");
            ps.setObject(1, value);
            ps.executeUpdate();
        } catch(SQLException ex) {
            // TODO
        }
    }

    public static void set(String table, String gate, Object gate_value, String column, Object value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET " + column + "=? WHERE " + gate + "=?");
            ps.setObject(1, value);
            ps.setObject(2, gate_value);
            ps.executeUpdate();
        } catch(SQLException ex) {
            // TODO
        }
    }

    public static boolean exists(String table, String column, Object value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT * FROM "
            + table + " WHERE " + column + "=?");
            ps.setObject(1, value);

            ResultSet results = ps.executeQuery();
            return results.next();
        } catch(SQLException ex) {
            // TODO
        }
        return false;
    }

    public static String getString(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
                    + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            String toReturn;

            if (rs.next()) {
                toReturn = rs.getString(column);
                return toReturn;
            }
        } catch (SQLException ex) {
            // TODO
        }
        return null;
    }

    public static int getInt(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
                    + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            int toReturn;

            if (rs.next()) {
                toReturn = rs.getInt(column);
                return toReturn;
            }
        } catch (SQLException ex) {
            // TODO
        }
        return 0;
    }

    public static Double getDouble(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
                    + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            double toReturn;

            if (rs.next()) {
                toReturn = rs.getDouble(column);
                return toReturn;
            }
        } catch (SQLException ex) {
            // TODO
        }
        return null;
    }

    public static long getLong(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
                    + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            long toReturn;

            if (rs.next()) {
                toReturn = rs.getLong(column);
                return toReturn;
            }
        } catch (SQLException ex) {
            // TODO
        }
        return 0;
    }

    public static byte getByte(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
                    + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            byte toReturn;

            if (rs.next()) {
                toReturn = rs.getByte(column);
                return toReturn;
            }
        } catch (SQLException ex) {
            // TODO
        }
        return 0;
    }

    public static Object get(String table, String column, String gate, Object gate_value) {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + table
             + " WHERE " + gate + "=?");
            ps.setObject(1, gate_value);

            ResultSet rs = ps.executeQuery();
            Object toReturn;

            if (rs.next()) {
                toReturn = rs.getObject(column);
                return toReturn;
            }
        } catch(SQLException ex) {
            // TODO
        }
        return null;
    }
}
