package main.dao;

import main.connection.ConnectionWeb;
import main.enums.CategoryEmployee;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CategoryDao implements Dao<Category> {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    final String FIND_BY_ID_CATEGORY = "SELECT * " +
            "FROM categories " +
            "WHERE id_category=?;";
    private final String SELECT_ALL_CATEGORY = "SELECT id_category, name, persent_money " +
            "FROM categories; ";
    private final String INSERT_CATEGORY = "INSERT INTO categories VALUES(?,?,?)";
    private final String UPDATE_CATEGORY = "UPDATE categories " +
            "SET persent_money=? " +
            "WHERE id_category=?;";
    private final String DELETE_FROM_CATEGORY = "DELETE FROM categories " +
            "WHERE id_category=?;";

    ////
    public Category fromResultSet(ResultSet resultSet) throws SQLException {
        CategoryEmployee categoryEmployee = null;
        String ukrCategory = resultSet.getString("name");
        switch (ukrCategory){
            case "вища":{
               categoryEmployee=CategoryEmployee.UPPER;
               break;
            }
            case "перша": {
                categoryEmployee = CategoryEmployee.FIRST;
                break;
            }
            case "друга": {
                categoryEmployee = CategoryEmployee.SECOND;
                break;
            }
            case "третя": {
                categoryEmployee = CategoryEmployee.THIRD;
                break;
            }
            case "немає":{
                categoryEmployee=CategoryEmployee.NONE;
                break;
            }
            //default: categoryEmployee=CategoryEmployee.NONE;

        }
        return new Category.CategoryBuilder()
                .setnameCategory(categoryEmployee)
                //.setnameCategory(CategoryEmployee.valueOf(resultSet.getString("name")))
                .setpersentMoney(resultSet.getDouble("persent_money"))
                .setIdCategory(resultSet.getInt("id_category"))
                .build();

    }

    @Override
    public Optional findById(int id) throws DaoException, DatabaseConnectionException, SQLException {
        Category category;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_CATEGORY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            category = fromResultSet(resultSet);
        }
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY)) {
            List<Category> categories = new LinkedList<Category>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(fromResultSet(resultSet));
            }
            return categories;
        }

    }

    //???????????
    @Override
    public void insert(Category category) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {
            preparedStatement.setInt(1, category.getIdCategory());
            preparedStatement.setString(2, category.getName().toString());
            preparedStatement.setDouble(3, category.getPersentMoney());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(Category category) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            preparedStatement.setDouble(1, category.getPersentMoney());
            preparedStatement.setInt(2, category.getIdCategory());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_CATEGORY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
