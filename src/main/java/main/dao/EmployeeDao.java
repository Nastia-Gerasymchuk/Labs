package main.dao;

import main.connection.ConnectionWeb;
import main.enums.CategoryEmployee;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Category;
import main.model.Employee;
import main.model.Post;
import main.service.PostService;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmployeeDao implements Dao<Employee> {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    private final String FIND_BY_ID ="SELECT  id_employee, pers_id, depart_id, post_id, date_coming_at_work, working_rate, category_id, id_pers, pers_fname, pers_lname, pers_tname, birthday, address, login, pasw, id_category, name, persent_money "+
            "FROM ((employees join persons p on employees.pers_id = p.id_pers) ep JOIN categories c on c.id_category=ep.category_id) res " +
            "WHERE res.id_employee=?;";


    private final String INSERT_EMPLOYEES = "INSERT INTO employees(date_coming_at_work, working_rate, pers_id, category_id, depart_id, post_id)" +
                                            "  VALUES(?,?,?,?,?,?);";
    private final String UPDATE_EMPLOYEES = "UPDATE employees " +
            "SET working_rate=? " +
            "WHERE id_employee=?;";
    private final String DELETE_EMPLOYEES = "DELETE FROM employees " +
            "WHERE id_employee=?;";
    private final String SELECT_ALL = "SELECT  id_employee, pers_id, depart_id, post_id, date_coming_at_work, working_rate, category_id, id_pers, pers_fname, pers_lname, pers_tname, birthday, address, login, pasw, id_category, name, persent_money " +
                       "FROM ((employees join persons p on employees.pers_id = p.id_pers) ep JOIN categories c on c.id_category=ep.category_id) res; ";
    private final String FIND_ID_CATEGORY = "SELECT id_category " +
            "FROM categories " +
            "WHERE name=?;";
    private final String FIND_EMPLOYEES_BY_ADDRESS = "SELECT * " +
            "FROM employees join posts on employees.post_id = posts.id_post join categories c on employees.category_id = c.id_category " +
            "join persons p on employees.pers_id = p.id_pers join departments d on employees.depart_id = d.id_depart " +
            "" +
            " WHERE address=?";
    private final String FIND_EMPLOYEES_BY_CATEGORY = "SELECT * " +
            "FROM employees join  categories c on employees.category_id = c.id_category " +
            "join persons p on employees.pers_id = p.id_pers " +
            " WHERE name=?";
//    private final String FIND_LAST_ID="SELECT max(id_employee)" +
//                                        " FROM employees;";

    private Employee fromResultSet(ResultSet resultSet) throws SQLException, DatabaseConnectionException, DaoException {

//              Optional<Post> post1=new PostService().findById(resultSet.getInt("post_id"));
//        if (post1.isPresent()) {
//            Post post = post1.get();
//               Post post=new Post.PostBuilder().setId(post1.map(Post::getIdPost).get())
//                       .setName(post1.map(Post::getName).get())
//                       .setSalary(post1.map(Post::getSalary).get())
//                       .setHoursPost(post1.map(Post::getHoursPost).get())
//                       .build();
//        Optional<Post>post1= Optional.of((Post) new PostService().findById(resultSet.getInt("post_id")).get());
//        Post post = (Post)post1.get();
        Optional<Post> post1=new PostService().findById(resultSet.getInt("post_id"));
        if (post1.isPresent()) {
            Post post = post1.get();

        Category category1=new CategoryDao().fromResultSet(resultSet);

            CategoryEmployee category = category1.getName();
            return new Employee.EmployeeBuilder()
                    //.setIdEml(resultSet.getInt("id_employee"))
                    .setName(resultSet.getString("pers_lname"))
                    .setSurname(resultSet.getString("pers_fname"))
                    .setFathername(resultSet.getString("pers_tname"))
                    .setPost(post)
                    .setDateComingAtWork(resultSet.getDate("date_coming_at_work").toLocalDate())
                    .setWorkingRate(resultSet.getDouble("working_rate"))
                    .setCategory(category)
                    .build();
        }

        return null; // if post1.empty()
    }

    @Override
    public Optional findById(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(fromResultSet(resultSet));
        }

    }


    @Override
    public List<Employee> findAll() throws DaoException, DatabaseConnectionException, SQLException {
        List<Employee> employeeList = new LinkedList<Employee>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(fromResultSet(resultSet));
            }
            return employeeList;
        }
    }

    @Override
    public void insert(Employee employee) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES)) {
            PreparedStatement category = connection.prepareStatement(FIND_ID_CATEGORY);
            category.setString(1, employee.getCategory().toString());
            //preparedStatement.setInt(1, employee.getIdEmployee());
            preparedStatement.setDate(1, Date.valueOf(employee.getDateComingAtWork()));
            preparedStatement.setDouble(2, employee.getWorkingRate());
            preparedStatement.setInt(3, employee.getIdPerson());
            preparedStatement.setInt(4, (category.executeQuery().getInt("id_category")));
            preparedStatement.setInt(5, employee.getDepartment().getId());
            preparedStatement.setInt(6, employee.getMyPost().getIdPost());
            //preparedStatement.setInt(4, category.executeQuery().getInt(1));
            preparedStatement.executeUpdate();
            category.close();
        }
    }

    @Override
    public void update(Employee employee) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES)) {
            preparedStatement.setDouble(1, employee.getWorkingRate());
            preparedStatement.setInt(2, employee.getIdEmployee());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void delete(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEES)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

//    private Employee fromResultSetPost(ResultSet resultSet) throws SQLException {
//        Post post = new Post.PostBuilder().setName(resultSet.getString("post_name")).setSalary(resultSet.getDouble("post_salary")).setHoursPost(resultSet.getInt("hours_post")).build();
//        CategoryEmployee category = CategoryEmployee.valueOf(resultSet.getString("name"));//new Category.CategoryBuilder().setnameCategory(resultSet.getString("name"));
//        return new Employee.EmployeeBuilder()
//                .setName(resultSet.getString("pers_lname"))
//                .setSurname(resultSet.getString("pers_fname"))
//                .setFathername(resultSet.getString("pers_tname"))
//                .setPost(post)
//                .setDateComingAtWork(resultSet.getDate("date_coming_at_work").toLocalDate())
//                .setWorkingRate(resultSet.getDouble("working_rate"))
//                .setCategory(category)
//                .build();
//
//    }


    public List<Employee> findEmployeesByAddress(String address) throws DatabaseConnectionException, SQLException {
        List<Employee> employees = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEES_BY_ADDRESS)) {
            preparedStatement.setString(1, address);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(fromResultSet(resultSet));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> findEmployeesByCategory(CategoryEmployee categoryDepartment) throws DatabaseConnectionException, SQLException {
        List<Employee> employees = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEES_BY_CATEGORY)) {
            preparedStatement.setString(1, categoryDepartment.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(fromResultSet(resultSet));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private CategoryEmployee searchCategory(String category) {
        CategoryEmployee categoryDepartment = null;
        if (category == CategoryEmployee.NONE.toString()) {
            categoryDepartment = CategoryEmployee.NONE;
        } else if (category == CategoryEmployee.FIRST.toString()) {
            categoryDepartment = CategoryEmployee.FIRST;
        } else if (category == CategoryEmployee.SECOND.toString()) {
            categoryDepartment = CategoryEmployee.SECOND;
        } else if (category == CategoryEmployee.THIRD.toString()) {
            categoryDepartment = CategoryEmployee.THIRD;
        } else if (category == CategoryEmployee.UPPER.toString()) {
            categoryDepartment = CategoryEmployee.UPPER;
        }
        return categoryDepartment;
    }

}
