package main.dao;

import main.connection.ConnectionWeb;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PostDao implements Dao<Post> {
    private static final Connection connection = ConnectionWeb.getInstance().getConnection();
    protected final String FIND_BY_ID="SELECT * " +
                                    "FROM posts " +
                                    "WHERE id_post=?;";
    protected final String SELECT_ALL="SELECT * " +
                                    "FROM posts; ";
    private final String INSERT_POST="INSERT INTO posts VALUES(?,?,?)";
    private final String UPDATE_POST="UPDATE posts " +
                                     "SET post_salary=? "+
                                     "WHERE id_post=?;";
    private final String DELETE_FROM_POSTS="DELETE FROM posts " +
                                           "WHERE id_post=?;";

    private Post fromResultSet(ResultSet resultSet) throws SQLException {
        return new Post.PostBuilder().setName(resultSet.getString("post_name"))
                .setSalary(resultSet.getDouble("post_salary"))
                .setHoursPost(resultSet.getInt("hours_post"))
                .setId(resultSet.getInt("id_post"))
                .build();
    }



    @Override
    public Optional<Post> findById(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            return Optional.ofNullable(fromResultSet(resultSet));
        }
    }

    @Override
    public List<Post> findAll() throws DaoException, DatabaseConnectionException, SQLException {
        List<Post> posts=new LinkedList<Post>();
        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                posts.add(fromResultSet(resultSet));
            }
            return posts;
        }

    }

    @Override
    public void insert(Post post) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_POST)) {

            preparedStatement.setString(1,post.getName());
            preparedStatement.setDouble(2,post.getSalary());
            preparedStatement.setDouble(3,post.getHoursPost());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void update(Post post) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_POST)) {
            preparedStatement.setDouble(1,post.getSalary());
            preparedStatement.setInt(2,(int)post.getIdPost());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws DaoException, DatabaseConnectionException, SQLException {
        try(PreparedStatement preparedStatement=connection.prepareStatement(DELETE_FROM_POSTS)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }
}
