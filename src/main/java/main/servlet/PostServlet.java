package main.servlet;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Post;
import main.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/posts")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Post> posts = new PostService().findAll();
            req.setAttribute("size", posts.size());
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("postList.jsp").forward(req, resp);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // doDelete(req, resp);
        try {
            new PostService().delete(Integer.parseInt(req.getParameter("del_id")));
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        doGet(req,resp);

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            new PostService().delete(Integer.parseInt(req.getParameter("del_id")));
//        } catch (DaoException | SQLException | DatabaseConnectionException e) {
//            e.printStackTrace();
//        }
//
//        doGet(req,resp);


    }}

