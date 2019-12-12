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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(urlPatterns = "/editPost")
public class EditPostServlet8 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPost=Integer.parseInt(req.getParameter("edit_post"));

        Object post= null;
        try {
            post = (Post)(new PostService().findById(idPost)).get();
        } catch (DaoException | DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("post",post);
        req.getRequestDispatcher("editPost8.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPost=Integer.parseInt(req.getParameter("id_post"));
        String postName=req.getParameter("post_name");
        double postSalary=Double.parseDouble(req.getParameter("post_salary"));
        int postHours=Integer.parseInt(req.getParameter("post_hours"));
        Post post=new Post.PostBuilder()
                .setId(idPost)
                .setName(postName)
                .setHoursPost(postHours)
                .setSalary(postSalary)
                .build();
        try {
            new PostService().update(post);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("postList.jsp").forward(req,resp);
    }
}
