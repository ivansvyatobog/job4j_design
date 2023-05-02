package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s()", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table if exists %s", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add column if not exists %s %s", tableName, columnName, type);
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        execute(sql);
    }

    private void execute(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("sql.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable("person");
                tableEditor.addColumn("person", "name", "varchar");
                tableEditor.addColumn("person", "age", "int");
                tableEditor.addColumn("person", "gender", "text");
                tableEditor.dropColumn("person", "gender");
                tableEditor.renameColumn("person", "name", "nickname");
                System.out.println(tableEditor.getTableScheme("person"));
                tableEditor.dropTable("person");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}