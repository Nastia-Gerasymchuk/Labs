package main.dao;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(int id) throws DaoException, DatabaseConnectionException, SQLException;

    List<T> findAll() throws DaoException, DatabaseConnectionException, SQLException;

    void insert(T t) throws DaoException, DatabaseConnectionException, SQLException;

    void update(T t) throws DaoException, DatabaseConnectionException, SQLException;

    void delete(int id) throws DaoException, DatabaseConnectionException, SQLException;


}
