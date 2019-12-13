package main.service;

import main.dao.CategoryDao;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryService implements ServiceInterface<Category> {
    private CategoryDao categoryDao=new CategoryDao();
    @Override
    public Optional<Category> findById(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0){
            return categoryDao.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() throws DaoException, SQLException, DatabaseConnectionException {
        return categoryDao.findAll();
    }

    @Override
    public void insert(Category category) throws DaoException, SQLException, DatabaseConnectionException {
        if(category!=null){
            categoryDao.insert(category);
        }
    }

    @Override
    public void update(Category category) throws DaoException, SQLException, DatabaseConnectionException {
        if(category!=null){
            categoryDao.update(category);
        }
    }

    @Override
    public void delete(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0){
            categoryDao.delete(id);
        }
    }
}
