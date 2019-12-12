package main.util;


import main.connection.ConnectionWeb;
import main.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {

    // в цьому класі потрібно створити структуру твоїх таблиць
    // example: https://github.com/sh1nes-1/Java_Labs/blob/master/src/main/java/lab5/utils/DatabaseStructure.java
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    private final String CREATE_DEPARTMENTS="CREATE TABLE if not exists departments(id_depart INTEGER PRIMARY KEY," +
            "department_name CHARACTER VARYING (40) NOT NULL);";
    private final String DROP_DEPARTMENTS="DROP TABLE if EXISTS departments;";
    private final String CREATE_POSTS="CREATE TABLE if not exists posts(id_post INTEGER PRIMARY KEY," +
            "post_name CHARACTER VARYING(20) NOT NULL," +
            "post_salary DOUBLE PRECISION NOT NULL," +
            "hours_post INTEGER);";

    private final String DROP_POSTS="DROP TABLE posts;";
    private final String CREATE_PERSONS="CREATE TABLE if not exists persons " +
            "(id_pers INTEGER PRIMARY KEY," +
            "pers_fname CHARACTER VARYING(20) NOT NULL," +
            "pers_lname CHARACTER VARYING(20) NOT NULL," +
            "pers_tname CHARACTER VARYING(20)," +
            "birthday Date," +
            "address CHARACTER VARYING(80)," +
            "login CHARACTER VARYING(10)," +
            "pasw CHARACTER VARYING(15));";
    private final String DROP_PERSONS="DROP TABLE persons;";
    private final String CREATE_CATEGORIES="CREATE TABLE if not exists categories" +
            "(id_category INTEGER NOT NULL," +
            "name CHARACTER VARYING(20) NOT NULL," +
            "persent_money DOUBLE PRECISION NOT NULL );";
    private final String DROP_CATEGORIES="DROP TABLE categories;";
    private final String CREATE_EMPLOYEES="CREATE TABLE if not exists employees(" +
            "id_employee INTEGER PRIMARY KEY," +
            "pers_id INT REFERENCES persons(id_pers) NOT NULL," +
            "depart_id INT REFERENCES departments(id_depart) NOT NULL," +
            "post_id INT REFERENCES posts(id_post) NOT NULL," +
            "date_coming_at_work DATE," +
            "working_rate DOUBLE PRECISION  NOT NULL," +
            "category_id integer NOT NULL REFERENCES categories(id_category));";
    private final String DROP_EMPLOYEES="DROP TABLE employees;";
    private final String CREATE_ACCRUED_SALLARIES="CREATE TABLE accrued_sallaries(" +
            "id_accrued_sallary INT PRIMARY KEY," +
            "employee_id INT REFERENCES employees(id_employee) NOT NULL," +
            "accrued_sal_date DATE NOT NULL," +
            "days_worked INT NOT NULL," +
            "accrued_sal_sum FLOAT" +
            ");";
    private final String DROP_ACCRUED_SALLARIES="DROP TABLE accrued_sallaries";

    public void createDepartment() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()){
            statement.execute(CREATE_DEPARTMENTS);
        }
    }

    public void dropDepartment() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()){
            statement.execute(DROP_DEPARTMENTS);
        }
    }

    public void createPost() throws SQLException, DatabaseConnectionException {
        try(Statement statement=connection.createStatement()
        ) {
          statement.execute(CREATE_POSTS);
        }
    }
    public void dropPost() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(DROP_POSTS);
        }
    }
    public void createPerson() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(CREATE_PERSONS);

        }
    }
    public void dropPerson() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
         statement.execute(DROP_PERSONS);
        }
    }
    public void createCategory() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(CREATE_CATEGORIES);

        }
    }
    public void dropCategory() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(DROP_CATEGORIES);
        }
    }
    public void createEmployee() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(CREATE_EMPLOYEES);

        }
    }
    public void dropEmployee() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(DROP_EMPLOYEES);
        }
    }
    public void createAccruedSallaries() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(CREATE_ACCRUED_SALLARIES);

        }
    }
    public void dropAccruedSallaries() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()) {
            statement.execute(DROP_ACCRUED_SALLARIES);
        }
    }


    public void createAllTables() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()){
            statement.execute(CREATE_DEPARTMENTS);
            statement.execute(CREATE_POSTS);
            statement.execute(CREATE_PERSONS);
            statement.execute(CREATE_CATEGORIES);
            statement.execute(CREATE_EMPLOYEES);
            statement.execute(CREATE_ACCRUED_SALLARIES);
        }
    }
    public void dropAllTables() throws DatabaseConnectionException, SQLException {
        try(Statement statement=connection.createStatement()){
            statement.execute(DROP_ACCRUED_SALLARIES);
            statement.execute(DROP_EMPLOYEES);
            statement.execute(DROP_CATEGORIES);
            statement.execute(DROP_PERSONS);
            statement.execute(DROP_POSTS);
            statement.execute(DROP_DEPARTMENTS);

        }
    }

}
