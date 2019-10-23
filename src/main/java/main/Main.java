package main;

import main.enums.CategoryDepartment;
import main.io.JSONAllDepartments;
import main.io.TXTAllDepartments;
import main.io.XMLAllDepartments;
import main.model.AllDepartments;
import main.model.Department;
import main.model.Employee;
import main.model.Post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        Department dep0 = new Department.DepartmentBuilder()
                .setName("dep0")
                .setDepartment(new LinkedList<>(Arrays.asList()))
                .build();
        Post post1 = new Post.PostBuilder()
                .setName("post1")
                .setSalary(3000)
                .build();

        Employee emp1 = new Employee.EmployeeBuilder()
                .setAddress("a1")
                .setCategory(CategoryDepartment.THE_FIRST)
                .setDateComingAtWork(LocalDateTime.now())
                .setDepartment(dep0)
                .setName("N1")
                .setSurname("S1")
                .setFathername("F1")
                .setPost(post1)
                .setWorkingRate(3.2)
                .setYearBorn(LocalDateTime.now())
                .build();

        Department dep1 = new Department.DepartmentBuilder()
                .setName("dep1")
                .setDepartment(new LinkedList<>(Arrays.asList(emp1)))
                .build();

        AllDepartments allDepartments = new AllDepartments();
        allDepartments.setDepartmentAdd("depAdd");
        allDepartments.setHospitalAdd("hospAdd");
        allDepartments.setPoliclinicAdd("polAdd");
        allDepartments.setDepartments(new HashSet<Department>(Arrays.asList(dep1)));

        XMLAllDepartments xml = new XMLAllDepartments();
        xml.serialize(allDepartments, "test.xml");
        JSONAllDepartments json=new JSONAllDepartments();
        json.serialize(allDepartments,"test.json");
        TXTAllDepartments txt=new TXTAllDepartments();
        txt.serialize(allDepartments,"text.txt");

    }
}


