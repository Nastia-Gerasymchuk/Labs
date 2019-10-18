package main;

import main.enums.CategoryDepartment;
import main.io.XMLAllDepartments;
import main.model.*;
import service.EmployeesService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //CategoryDepartment temp;
        //temp=CategoryDepartment.THE_FIRST;
        /*Post temp=new Post("хірург",5000);

        List<Post> myCollection = new ArrayList<>();
        myCollection.add(new Post("медсестра",3200));
        myCollection.add(temp);
        myCollection.add(temp);
        myCollection.add(1,new Post("старша медсестра",3200));
        myCollection.add(3,new Post("травматолог",5000));
//        System.out.println(myCollection);
        Iterator<Post> iterator = myCollection.iterator();
        while (iterator.hasNext()){
            Post p = iterator.next();
        }
        Department dep = new Department();
        dep.setEmployees(new ArrayList<>());
        List employeesByAddress = new EmployeesService(dep.getEmployees()).findEmployeesByAddress("jjj");*/

        //EmployeesService e=new EmployeesService();
        //System.out.println(e);

        /*AllDepartments temp=new AllDepartments();
        temp.setDepartmentAdd("DDD");
        temp.setHospitalAdd("HHH");
        temp.setPoliclinicAdd("PPP");
        Employee.EmployeeBuilder employeeBuilder = new Employee.EmployeeBuilder();
        employeeBuilder.setDepartment(null);
        employeeBuilder.setPost(new Post.PostBuilder().setName("surgeon").setSalary(2000).build());
        employeeBuilder.setName("Anna");
        employeeBuilder.setFathername("Ivanivna");
        employeeBuilder.setSurname("Ivah");
        employeeBuilder.setDateComingAtWork(LocalDateTime.now());
        employeeBuilder.setWorkingRate(0.75);
        employeeBuilder.setCategory(CategoryDepartment.THE_FIRST);
        employeeBuilder.setAddress("t.Chernivt");
        employeeBuilder.setYearBorn(LocalDateTime.now());
        Employee e= employeeBuilder
                .build();
        Department t=new Department.DepartmentBuilder()
                .setName("therapy")
                .setDepartment(new LinkedList<Employee>(Arrays.asList(e)))
                .build();
        temp.setDepartments(new HashSet<>(Arrays.asList(t)));

        XMLAllDepartments res=new XMLAllDepartments();
        try {
            res.serialize(temp,"test.xml");
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }*/
        //Post p=new Post.PostBuilder().setName("doctor").setSalary(0).build();
        //System.out.println(p);
        /*Person person=new Person.PersonBuilder().setName("Nastia").
                setSurname("Gerasymchuk").
                setFathername("Borysivna").
                setAddress("Khotyn").
                setYearBorn(null).build();*/
        //System.out.println(person);
        /*Employee empl=new Employee.EmployeeBuilder().setName("Nastia").setSurname("Ivach").build();
        System.out.println(empl);
        List<Employee> list=new LinkedList<>();
        list.add(empl);
        Department department=new Department.DepartmentBuilder().setName("therapy").setDepartment(null).build();
        System.out.println(department);*/
        Salary s=new Salary();
        System.out.println(s);
    }
}
