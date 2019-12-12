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

@WebServlet(urlPatterns = "/addPost")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post=new Post.PostBuilder()

                .setName(req.getParameter("name"))
                .setHoursPost(Integer.parseInt(req.getParameter("hoursPost")))
                .setSalary(Double.parseDouble(req.getParameter("salary")))
                .build();

        try {
            new PostService().insert(post);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new PostServlet().doGet(req,resp);
    }
}
