package io.github.simplexdev.simplexcore.sql;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static Connection connection;

    public static void setConnection(String host, String user, String pass, String database, @Nullable String port) {
        if (port == null) port = "3306";
        if (host == null || user == null || pass == null) return;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
            + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", user, pass);
            // TODO SQL successfully connected message
        } catch(SQLException ex) {
            // TODO SQL couldn't connect message
        }
    }

    public static void connect(String host, String user, String pass, String database, @Nullable String port) {
        if (!isConnected()) {
            setConnection(host, user, pass, database, port);
        }
    }

    public static void disconnect() {
        try {
            if (isConnected()) {
                connection.close();
            }
        } catch(SQLException ex) {
            // TODO Couldn't disconnect SQL connection message. (Possible Solutions: There's no sql connection (SQL is not connected)).
        }
    }

    public static boolean isConnected() {
        if (connection != null)
            try {
                return !connection.isClosed();
            } catch (SQLException e) {
                // TODO SQL Connection error message.
            }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }

}
