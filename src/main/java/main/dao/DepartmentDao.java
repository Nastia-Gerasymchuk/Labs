package main.dao;

import main.connection.ConnectionWeb;
import main.enums.CategoryEmployee;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.*;
import main.service.DepartmentServiceInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DepartmentDao implements Dao<Department>, DepartmentServiceInterface {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    private final String FIND_BY_ID="SELECT *" +
            "FROM departments" +
            " WHERE id_depart=?";
    private final String INSERT_Department="INSERT INTO departments VALUES(?);";
    private final String UPDATE_DEPARTMENT="UPDATE departments " +
                                            "SET depart_name=? "+
                                            "WHERE id_depart=?;";
    private final String DELETE_DEPARTMENT="DELETE FROM departments " +
                                           "WHERE id_depart=?;";
    private final String SELECT_ALL="SELECT * " +
                                    "FROM departments;";

    private final String MAX_SALARY_DEPARTMENT ="SELECT MAX(res.post_salary)" +
            "FROM ((departments d INNER JOIN employees e ON d.id_depart=e.depart_id)de INNER JOIN posts p " +
            "ON p.id_post=de.post_id) res " +
            "WHERE res.depart_id=?";
    private final String MAX_SALARY_EMPLOYEE_DEPARTMENT ="SELECT DISTINCT res.pers_fname, res.pers_lname,res.pers_tname" +
            "            FROM (((departments d INNER JOIN employees e ON d.id_depart=e.depart_id)de INNER JOIN posts p" +
            "            ON p.id_post=de.post_id)dep INNER JOIN persons p ON dep.pers_id=p.id_pers)res\n" +
            "            WHERE res.depart_id=? AND res.post_salary=?";

    private final String EMPLOYEES_BY_CURRENT_YEAR_AND_CATEGORY ="SELECT res.pers_fname, res.pers_lname,res.pers_tname " +
            "            FROM (((departments d INNER JOIN employees e ON d.id_depart=e.depart_id)de INNER JOIN " +
            "                     persons p ON de.pers_id=p.id_pers)ep INNER JOIN categories c ON ep.category_id=c.idCategory)res " +
            "            WHERE res.id_depart=? AND res.name=? AND EXTRACT(YEAR FROM res.date_coming_at_work) " +
            " =EXTRACT (YEAR FROM CURRENT_TIMESTAMP);";

    private final String EMPLOYEES_BY_MAX_WORKING_RATE ="SELECT res.pers_fname, res.pers_lname,res.pers_tname " +
            "            FROM (((departments d INNER JOIN employees e ON d.id_depart=e.depart_id)de INNER JOIN " +
            "                     persons p ON de.pers_id=p.id_pers)ep INNER JOIN categories c ON ep.category_id=c.id_category)res " +
            "            WHERE res.id_depart=?  AND res.working_rate=(SELECT MAX(res.working_rate) " +
            " FROM (((departments d INNER JOIN employees e ON d.id_depart=e.depart_id)de INNER JOIN " +
            "                     persons p ON de.pers_id=p.id_pers)ep INNER JOIN categories c ON ep.category_id=c.id_category)res " +
            "            WHERE res.id_depart=? )";

    private Department fromResultSet(ResultSet resultSet) throws SQLException {
        return new Department.DepartmentBuilder()
                .setName(resultSet.getString("depart_name"))
                .setIdDepartment(resultSet.getInt("id_depart"))
                .build();
    }
    @Override
    public Optional findById(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            //Department department=new Department.DepartmentBuilder().setName(resultSet.getString("depart_name")).build();
            return Optional.ofNullable(fromResultSet(resultSet));
        }
    }



    @Override
    public List<Department> findAll() throws DaoException, DatabaseConnectionException, SQLException {
        List<Department> departmentList=new LinkedList<Department>();
        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                departmentList.add(fromResultSet(resultSet));            }
            return departmentList;
        }
    }

    @Override
    public void insert(Department department) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_Department)){
           //preparedStatement.setInt(1,department.getId());
           preparedStatement.setString(1,department.getName());
           preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Department department) throws DaoException, DatabaseConnectionException, SQLException {
            try(PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_DEPARTMENT)) {
                preparedStatement.setInt(2,(int)department.getId());
                preparedStatement.setString(1,department.getName());
                preparedStatement.executeUpdate();
            }
    }

    @Override
    public void delete(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(DELETE_DEPARTMENT)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }

    }

    private Employee fromResultSetEmployee(ResultSet resultSet) throws SQLException {
        Post post=new Post.PostBuilder().setName(resultSet.getString("post_name")).setSalary(resultSet.getDouble("post_salary")).setHoursPost(resultSet.getInt("hours_post")).build();
        CategoryEmployee category= CategoryEmployee.valueOf(resultSet.getString("name"));//new Category.CategoryBuilder().setnameCategory(resultSet.getString("name"));
        return new Employee.EmployeeBuilder()
                .setName(resultSet.getString("pers_lname"))
                .setSurname(resultSet.getString("pers_fname"))
                .setFathername(resultSet.getString("pers_tname"))
                .setPost(post)
                .setDateComingAtWork(resultSet.getDate("date_coming_at_work").toLocalDate())
                .setWorkingRate(resultSet.getDouble("working_rate"))
                .setCategory(category)
                .build();
    }
    public Double findMaxSalaryOfDepartment(Department department) throws DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatementSQL=connection.prepareStatement(MAX_SALARY_DEPARTMENT)) {
            preparedStatementSQL.setInt(1,department.getId());
            ResultSet resultSet=preparedStatementSQL.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);
        }
    }

    public List<Employee> EmployeeWithMaxSal(Department department) throws DatabaseConnectionException, SQLException {
        List<Employee> employees = new LinkedList<Employee>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MAX_SALARY_EMPLOYEE_DEPARTMENT)) {
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setDouble(2, findMaxSalaryOfDepartment(department));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(fromResultSetEmployee(resultSet));}
            return employees;
        }
    }


//    public List<Employee> findEmpByCategoryAndCurrentYear(CategoryEmployee categoryEmployee) throws DatabaseConnectionException, SQLException {
//        List<Employee> employeeList=new LinkedList<Employee>();
//        try(Connection connection=ConnectionFactory.getConnectionBuilder().getConnection();
//            PreparedStatement preparedStatement=connection.prepareStatement(EMPLOYEES_BY_CURRENT_YEAR_AND_CATEGORY)) {
//            preparedStatement.setInt(1,department.getIdPost());
//            preparedStatement.setString(2,categoryEmployee.toString());
//            ResultSet resultSet=preparedStatement.executeQuery();
//            while (resultSet.next()){
//                employeeList.add(fromResultSetEmployee(resultSet));
//            }
//        }
//        return employeeList;
//    }


    public List<Employee> findEmployeesMaxWorkingRate (Department department) throws DatabaseConnectionException, SQLException {
        List<Employee> employeeList = new LinkedList<Employee>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_BY_MAX_WORKING_RATE)) {
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setInt(2, department.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(fromResultSetEmployee(resultSet));
            }
            return employeeList;
        }
    }

}
