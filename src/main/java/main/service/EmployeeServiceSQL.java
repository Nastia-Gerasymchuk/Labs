package main.service;


import main.connection.ConnectionWeb;
import main.enums.CategoryEmployee;
import main.exceptions.DatabaseConnectionException;
import main.model.Employee;
import main.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EmployeeServiceSQL implements EmployeeServiceInterface {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    private List<Employee> employees;
    private final String FIND_EMPLOYEES_BY_ADDRESS="SELECT * " +
            "FROM employees join posts on employees.post_id = posts.id_post join categories c on employees.category_id = c.id_category " +
            "join persons p on employees.pers_id = p.id_pers join departments d on employees.depart_id = d.id_depart " +
            "" +
            " WHERE address=?";
    private final String FIND_EMPLOYEES_BY_CATEGORY ="SELECT * " +
            "FROM employees join  categories c on employees.category_id = c.id_category " +
            "join persons p on employees.pers_id = p.id_pers "+
            " WHERE name=?";
    private Employee fromResultSet(ResultSet resultSet) throws SQLException {
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




    public EmployeeServiceSQL(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> findEmployeesByAddress(String address) throws DatabaseConnectionException, SQLException {
        List<Employee> employees=new LinkedList<>();
       try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_EMPLOYEES_BY_ADDRESS)) {
           preparedStatement.setString(1,address);
           ResultSet resultSet=preparedStatement.executeQuery();
           while (resultSet.next()){
               employees.add(fromResultSet(resultSet));
           }
       }
        return employees;
    }

    public List<Employee> findEmployeesByCategory(CategoryEmployee categoryDepartment) throws DatabaseConnectionException, SQLException {
        List<Employee> employees=new LinkedList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_EMPLOYEES_BY_CATEGORY)) {
            preparedStatement.setString(1,categoryDepartment.toString());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(fromResultSet(resultSet));
            }
        }
        return employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeServiceSQL that = (EmployeeServiceSQL) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }

    @Override
    public String toString() {
        return "EmployeesServiceSQL:" +
                "employees=" + employees;
    }



}
