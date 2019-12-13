package main.service;

import main.enums.CategoryEmployee;
import main.exceptions.DatabaseConnectionException;
import main.model.Department;
import main.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentServiceInterface {
      default Double findMaxSalaryOfDepartment(Department department) throws DatabaseConnectionException, SQLException {
            return 0.0;
      }

      default List<Employee> EmployeeWithMaxSal(Department department) throws DatabaseConnectionException, SQLException{return new ArrayList<Employee>();
      };

      default List<Employee> findEmpByCategoryAndCurrentYear(CategoryEmployee categoryEmployee, Department department) throws DatabaseConnectionException, SQLException {
            return new ArrayList<>();
      }

}
