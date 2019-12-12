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
import java.util.Optional;

@WebServlet(urlPatterns = "/post_edit")
public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Optional<Post> post=(Post)(new PostService().findById(Integer.parseInt(req.getParameter("edit_id"))));
            Optional<Post> post1= new PostService().findById(Integer.parseInt(req.getParameter("edit_id")));
            if(post1.isPresent()){
                Post post=post1.get();
                req.setAttribute("id", post.getIdPost());
                req.setAttribute("name",post.getName());
                req.setAttribute("salary",post.getSalary());
                req.setAttribute("hoursPost",post.getHoursPost());
                req.setAttribute("minSalary",Post.MIN_SALARY);
                req.setAttribute("maxSalary",Post.MAX_SALARY);
                req.getRequestDispatcher("editPost.jsp").forward(req,resp);

            }
            else {
                throw new NullPointerException();
            }

        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Post post=new Post.PostBuilder()
               .setId(Integer.parseInt(req.getParameter("id")))
               .setSalary(Double.parseDouble(req.getParameter("salary")))
               .setHoursPost(Integer.parseInt(req.getParameter("hoursPost")))
               .setName(req.getParameter("name"))
               .build();
        try {
            new PostService().update(post);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        //зчитати дані з форми і оновити через сервіс

        new PostServlet().doGet(req,resp);
    }
}
