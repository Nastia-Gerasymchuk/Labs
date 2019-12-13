package main.service;

import main.enums.CategoryEmployee;
import main.exceptions.DatabaseConnectionException;
import main.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeServiceInterface {
    abstract List<Employee> findEmployeesByAddress(String address) throws DatabaseConnectionException, SQLException;
    abstract List<Employee> findEmployeesByCategory(CategoryEmployee categoryDepartment) throws DatabaseConnectionException, SQLException;
}
