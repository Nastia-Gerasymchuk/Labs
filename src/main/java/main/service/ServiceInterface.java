package main.service;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {

    Optional<T> findById(int id) throws DaoException, SQLException, DatabaseConnectionException;

    List<T> findAll() throws DaoException, SQLException, DatabaseConnectionException;

    void insert(T t) throws DaoException, SQLException, DatabaseConnectionException;

    void update(T t) throws DaoException, SQLException, DatabaseConnectionException;

    void delete(int id) throws DaoException, SQLException, DatabaseConnectionException;


}
