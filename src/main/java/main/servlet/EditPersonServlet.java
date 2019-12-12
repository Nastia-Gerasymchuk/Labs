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
import java.util.Optional;

@WebServlet(urlPatterns = "/person_edit")
public class EditPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Optional<Person> person= new PersonsService().findById(Integer.parseInt(req.getParameter("edit_pers")));
            if(person.isPresent()){
                Person personById=person.get();
                req.setAttribute("id",personById.getIdPerson());
                req.setAttribute("name",personById.getName());
                req.setAttribute("surname",personById.getSurname());
                req.setAttribute("middle_name",personById.getFathername());
                req.setAttribute("birthday",personById.getYearBorn());
                req.setAttribute("address",personById.getAddress());
                req.setAttribute("login",personById.getLogin());
                req.setAttribute("password",personById.getPasw());
                req.getRequestDispatcher("editPerson.jsp").forward(req,resp);
            }
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person=new Person.PersonBuilder()
                .setIdPerson(Integer.parseInt(req.getParameter("id")))
                .setName(req.getParameter("name"))
                .setSurname(req.getParameter("surname"))
                .setFathername(req.getParameter("middle_name"))
                .setAddress(req.getParameter("address"))
                .setYearBorn(LocalDate.parse(req.getParameter("birthday")))
                .setLogin(req.getParameter("login"))
                .setPasw(req.getParameter("password"))
                .build();
        try {
            new PersonsService().update(person);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new PersonServlet().doGet(req,resp);

    }
}
