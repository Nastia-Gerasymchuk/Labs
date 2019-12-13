package main.service;

import main.dao.PersonDao;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonsService implements ServiceInterface<Person> {
    PersonDao personDao=new PersonDao();
    @Override
    public Optional<Person> findById(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0) {
            return personDao.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() throws DaoException, SQLException, DatabaseConnectionException {
       return personDao.findAll();
    }

    @Override
    public void insert(Person person) throws DaoException, SQLException, DatabaseConnectionException {
        if(person!=null){
            personDao.insert(person);
        }

    }

    @Override
    public void update(Person person) throws DaoException, SQLException, DatabaseConnectionException {
        if (person!=null){
            personDao.update(person);
        }
    }

    @Override
    public void delete(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0){
            personDao.delete(id);
        }
    }
}
