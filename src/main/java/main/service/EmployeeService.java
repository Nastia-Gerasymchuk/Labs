package main.service;

import main.dao.EmployeeDao;
import main.enums.CategoryEmployee;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Employee;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmployeeService implements ServiceInterface<Employee> {
    EmployeeDao employeeDao=new EmployeeDao();
    private final String FIND_EMPLOYEES_BY_ADDRESS="SELECT * " +
            "FROM employees join posts on employees.post_id = posts.id_post join categories c on employees.category_id = c.idCategory " +
            "join persons p on employees.pers_id = p.id_pers join departments d on employees.depart_id = d.id_depart " +
            "" +
            " WHERE address=?";
    private final String FIND_EMPLOYEES_BY_CATEGORY ="SELECT * " +
            "FROM employees join  categories c on employees.category_id = c.idCategory " +
            "join persons p on employees.pers_id = p.id_pers "+
            " WHERE name=?";

    @Override
    public Optional<Employee> findById(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0){
            return employeeDao.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() throws DaoException, SQLException, DatabaseConnectionException {
        return employeeDao.findAll();
    }

    @Override
    public void insert(Employee employee) throws DaoException, SQLException, DatabaseConnectionException {
        if(employee!=null){
             employeeDao.insert(employee);
        }
    }

    @Override
    public void update(Employee employee) throws DaoException, SQLException, DatabaseConnectionException {
        if(employee!=null){
            employeeDao.update(employee);
        }
    }

    @Override
    public void delete(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if(id>0){
            employeeDao.delete(id);
        }
    }

    public List<Employee> findEmployeesByAddress(String address) throws DatabaseConnectionException, SQLException {
        if(address!=null){
            return employeeDao.findEmployeesByAddress(address);
        }
        return new LinkedList<Employee>();
    }

    public List<Employee> findEmployeesByCategory(String categoryDepartment) throws DatabaseConnectionException, SQLException {
        if(!(categoryDepartment.isEmpty())){
            return employeeDao.findEmployeesByCategory(CategoryEmployee.valueOf(categoryDepartment));
        }
        return new LinkedList<Employee>();
        }

}
