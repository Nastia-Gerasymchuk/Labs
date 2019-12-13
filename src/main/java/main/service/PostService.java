package main.service;

import main.dao.PostDao;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostService implements ServiceInterface<Post> {
    private static final PostDao postDao = new PostDao();

    @Override
    public Optional<Post> findById(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if (id > 0) {
            return postDao.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List findAll() throws DaoException, SQLException, DatabaseConnectionException {
        return postDao.findAll();
    }

    @Override
    public void insert(Post post) throws DaoException, SQLException, DatabaseConnectionException {
        if (post != null) {
            postDao.insert(post);
        }
    }

    @Override
    public void update(Post post) throws DaoException, SQLException, DatabaseConnectionException {
        if (post != null) {
            postDao.update(post);
        }

    }


    @Override
    public void delete(int id) throws DaoException, SQLException, DatabaseConnectionException {
        if (id > 0) {
            postDao.delete(id);
        }
    }
}
