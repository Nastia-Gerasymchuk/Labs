package main.servlet;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deletePost")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new PostService().delete(Integer.parseInt(req.getParameter("del_id")));
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new PostServlet().doGet(req,resp);
        //req.getRequestDispatcher("postList.jsp").forward(req,resp);



    }
}
