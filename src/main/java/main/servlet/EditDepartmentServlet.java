package main.servlet;

import main.dao.DepartmentDao;
import main.exceptions.DaoException;
import main.exceptions.DatabaseConnectionException;
import main.model.Department;
import main.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(urlPatterns = "/department_edit")
public class EditDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Department> department= null;
        try {
            department = new DepartmentDao().findById(Integer.parseInt(req.getParameter("edit_department")));

            if(department.isPresent()){
                Department departmentById=department.get();
                req.setAttribute("id",departmentById.getId());
                req.setAttribute("name",departmentById.getName());
                req.getRequestDispatcher("editDepartment.jsp").forward(req,resp);
            }
            else {
                throw new NullPointerException("Department was not found");
            }
        } catch (DaoException | DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department=new Department.DepartmentBuilder()
                .setIdDepartment(Integer.parseInt(req.getParameter("id")))
                .setName(req.getParameter("name"))
                .build();
        try {
            new DepartmentService().update(department);
        } catch (DaoException | SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        new DepartmentServlet().doGet(req,resp);
    }
}
