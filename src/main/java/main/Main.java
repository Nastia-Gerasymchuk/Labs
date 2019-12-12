package main;

import main.enums.CategoryEmployee;
import main.io.JSONAllDepartments;
import main.io.TXTAllDepartments;
import main.io.XMLAllDepartments;
import main.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person=new Person.PersonBuilder().setYearBorn(LocalDate.now()).build();
        System.out.println(person.getYearBorn());

//        Department dep0 = new Department.DepartmentBuilder()
//                .setName("dep0")
//                .setEmployees(new LinkedList<>(Arrays.asList()))
//                .build();
        Post post1 = new Post.PostBuilder()
                .setName("post1")
                .setSalary(3000)
                .build();

        Employee emp1 = new Employee.EmployeeBuilder()
                .setAddress("a1")
                .setCategory(CategoryEmployee.FIRST)
                .setDateComingAtWork(LocalDate.now())
                .setName("N1")
                .setSurname("S1")
                .setFathername("F1")
                .setPost(post1)
                .setWorkingRate(3.2)
                .setYearBorn(LocalDate.now())
                .build();

        Department dep1 = new Department.DepartmentBuilder()
                .setName("dep1")
               // .setEmployees(new LinkedList<>(Arrays.asList(emp1)))
                .build();
        dep1.setEmployees(new LinkedList<>(Arrays.asList(emp1)));

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

        CategoryEmployee categoryEmployee1=CategoryEmployee.valueOf("UPPER");
        CategoryEmployee categoryEmployee2=CategoryEmployee.values()[8];
        CategoryEmployee categoryEmployee3=CategoryEmployee.values()[Arrays.asList(CategoryEmployee.values()).indexOf("UPPER")];



    }
}


