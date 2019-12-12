package main.servlet;

import main.enums.CategoryEmployee;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Department;
import main.model.Employee;
import main.model.Person;
import main.model.Post;
import main.service.DepartmentService;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/add_Employee")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments=null;
        List<Post> posts=null;
        try {
            departments=new DepartmentService().findAll();
            posts=new PostService().findAll();
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        req.setAttribute("departments",departments);
        req.setAttribute("posts",posts);
        req.getRequestDispatcher("addEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Integer idPers= Integer.valueOf("SELECT MAX(id_pers)" +
//                    " FROM persons ".toString());
            new PersonsService().insert(new Person.PersonBuilder()
                    //.setIdPerson(idPers+1)
                    .setName(req.getParameter("firstName"))
                    .setSurname(req.getParameter("secondName"))
                    .setFathername(req.getParameter("middleName"))
                    .setYearBorn(LocalDate.parse(req.getParameter("birthday")))
                    .setAddress(req.getParameter("address"))
                    .setLogin(req.getParameter("login"))
                    .setPasw(req.getParameter("password"))
                    .build());

           System.out.println("ID => "+Integer.parseInt(req.getParameter("idDepartments"))+req.getParameter("idPosts"));

            Department department= new DepartmentService().findById(Integer.parseInt(req.getParameter("idDepartments"))).get();
            new EmployeeService().insert(new Employee.EmployeeBuilder()
                    //.setIdEml(Integer.parseInt(req.getParameter("idEmployee")))
                    .setDepartment(department)
                    .setPost(new PostService().findById(Integer.parseInt(req.getParameter("idPosts"))).get())
                    .setCategory(CategoryEmployee.valueOf(req.getParameter("idCategory")))
                    .setWorkingRate(Double.parseDouble(req.getParameter("idWorkingRate")))
                    .setDateComingAtWork(LocalDate.ofEpochDay(req.getDateHeader("idDateComing")))
                    .build()
                    //.setPersonId(Integer.parseInt(req.getParameter("idPerson")))
            );
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new EmployeeServlet().doGet(req,resp);


    }
}

