package main.servlet;

import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Employee;
import main.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/edit_employee")
public class EditEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Employee employee = new EmployeeService().findById(Integer.parseInt(req.getParameter("id"))).get();
            req.setAttribute("id", employee.getIdEmployee());
            req.setAttribute("name", employee.getName());
            req.setAttribute("surname", employee.getSurname());
            req.setAttribute("post", employee.getMyPost());
            req.setAttribute("workingRate", employee.getWorkingRate());
            req.getRequestDispatcher("editEmployee.jsp").forward(req, resp);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new EmployeeService().update(new Employee.EmployeeBuilder()
                    //.setIdEml(Integer.parseInt(req.getParameter("id")))
                    .setWorkingRate(Double.parseDouble(req.getParameter("workingRate")))
                    .build()
            );
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new EmployeeServlet().doGet(req,resp);
    }
}
