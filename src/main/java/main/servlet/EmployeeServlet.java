package main.servlet;

import main.dto.EmployeeDto;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Employee;
import main.model.Person;
import main.service.EmployeeService;
import main.service.PersonsService;
import main.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Employee> employees=new EmployeeService().findAll();
            //List<EmployeeDto> employeeDtos=new LinkedList<>();
//            for (Employee e : employees) {
//                employeeDtos.add(e.buildDto());
//            }
            req.setAttribute("size",employees.size());
            req.setAttribute("employees",employees);
            req.getRequestDispatcher("employeeList.jsp").forward(req,resp);
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
        try {
            String id = req.getParameter("del_id");
            new EmployeeService().delete(Integer.parseInt(req.getParameter("del_id")));
         //   req.setAttribute("employees", null);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        doGet(req,resp);
    }


}
