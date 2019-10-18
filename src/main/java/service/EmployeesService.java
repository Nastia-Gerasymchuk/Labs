package service;

import main.enums.CategoryDepartment;
import main.model.Employee;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EmployeesService {
    private List<Employee> employees;

    public EmployeesService(){
        this(new LinkedList<>()); {
        }
    }

    public EmployeesService(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> findEmployeesByAddress(String address){
        List<Employee> res=new LinkedList<>();
        for (Employee e :employees) {
            if(e.getAddress().equals(address)){
                res.add(e);
            }
        }
        return res;
    }

    public List<Employee> findEmployeesByCategory(CategoryDepartment categoryDepartment){
        List<Employee> res=new LinkedList<>();
        Iterator<Employee> iterator = employees.iterator();
        while(iterator.hasNext()){
            Employee e=iterator.next();
            if(e.getCategory().equals(categoryDepartment)){
                res.add(e);
            }
        }
        return res;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesService that = (EmployeesService) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }

    @Override
    public String toString() {
        return "EmployeesService{" +
                "employees=" + employees +
                '}';
    }



}
