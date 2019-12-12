package main.servlet;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Person;
import main.service.PersonsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/addPerson")
public class AddPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new PersonsService().insert(new Person.PersonBuilder()
//                    .setIdPerson((Integer.parseInt("SELECT max(id_pers)" +
//                            " FROM persons"))+1)
                    .setName(req.getParameter("firstName"))
                    .setSurname(req.getParameter("secondName"))
                    .setFathername(req.getParameter("middleName"))
                    .setYearBorn(LocalDate.parse(req.getParameter("birthday")))
                    .setAddress(req.getParameter("address"))
                    .setLogin(req.getParameter("login"))
                    .setPasw(req.getParameter("password"))
                    .build()
            )
            ;
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new PersonServlet().doGet(req,resp);
    }
}
