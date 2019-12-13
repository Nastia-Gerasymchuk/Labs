package main.service;

import main.enums.CategoryEmployee;
import main.model.Department;
import main.model.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentServiceCollection implements DepartmentServiceInterface {
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public Double findMaxSalaryOfDepartment()
    {
        return department.getEmployees().stream().map(Employee::getSalaryPost).max(Double::compare).get();
    }
    public List<Employee> EmployeeWithMaxSal(){
        return department.getEmployees().stream()
                .filter((Employee employee)->{return employee.getSalaryPost()==findMaxSalaryOfDepartment();})
                .collect(Collectors.toList());
    }


    public List<Employee> findEmpByCategoryAndCurrentYear(CategoryEmployee categoryEmployee){
        return department.getEmployees().stream()
                .filter( (Employee employee)->{return employee.getCategory().equals(categoryEmployee);} )
                .filter(e->e.getDateComingAtWork().getYear()== LocalDateTime.now().getYear())
                .collect(Collectors.toList());
    }

//    public List<String> searchAllPosts(){
//        return employees.stream().map(e->e.getMyPost().getName()).distinct().collect(Collectors.toList());
//    }



}
