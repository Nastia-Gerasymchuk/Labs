package main.service;

import main.dao.DepartmentDao;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Department;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartmentService implements ServiceInterface<Department>{
    DepartmentDao departmentDao=new DepartmentDao();
    @Override
    public Optional<Department> findById(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0) {
           return departmentDao.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Department> findAll() throws DaoException, SQLException, DatabaseConnectionException {
        return departmentDao.findAll();
    }

    @Override
    public void insert(Department department) throws DaoException, SQLException, DatabaseConnectionException {
        if(department!=null){
            departmentDao.insert(department);
        }
    }

    @Override
    public void update(Department department) throws DaoException, SQLException, DatabaseConnectionException {
                if(department!=null){
                    departmentDao.update(department);
                }
    }

    @Override
    public void delete(int id) throws DaoException, SQLException, DatabaseConnectionException {
            if(id>0){
                departmentDao.delete(id);
            }
    }


}
