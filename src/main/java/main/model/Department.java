package main.model;


import main.enums.CategoryDepartment;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Class Department has:
 * @Override methods: equals, toString and hashcode,
 * static Class DepartmentBuilder, which allows users to set values of fields right.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    private long id;

    @NotNull(message = "name can not be null")
    private String name;

    @XmlElements({
            @XmlElement(name ="employee",type = Employee.class)
    })
    @XmlElementWrapper(name = "employees")


    private List<Employee> employees;
    private static int count;
    {id =++count;}
    public Double findMaxSalaryOfDepartment()
    {
        return employees.stream().map(Employee::getSalaryPost).max(Double::compare).get();
    }
    public List<Employee> findEmployeeMaxSal(){
        return employees.stream()
                .filter((Employee employee)->{return employee.getSalaryPost()==findMaxSalaryOfDepartment();})
                .collect(Collectors.toList());
    }


    public List<Employee> findEmpByCategoryAndThisYear(){
        return employees.stream()
                .filter( (Employee employee)->{return employee.getCategory().equals(CategoryDepartment.UPPER);} )
                .filter(e->e.getDateComingAtWork().getYear()==LocalDateTime.now().getYear())
                .collect(Collectors.toList());
    }

//    public List<String> searchAllPosts(){
//        return employees.stream().map(e->e.getMyPost().getName()).distinct().collect(Collectors.toList());
//    }

    public Employee findEmployeesMaxWorkingRate(){
        return employees.stream().max(Comparator.comparing(e->e.getWorkingRate())).get();
    }



    private Department(DepartmentBuilder departmentBuilder){
        this.name=departmentBuilder.name;
        this.employees=departmentBuilder.employees;
    }

    private long getId() {
        return id;
    }
    private void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return
                Objects.equals(name, that.name);
                //Objects.equals(employees, that.employees);*/
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees);
    }

    @Override
    public String toString() {
        String res = "Department:" +
                "name=" + name + ' ' + ", employees:";
        for (Employee e : employees) {
            res += e.toString() + "; ";
        }
        return res;
    }

    /**
     * The next class alloys user to set values of fields correctly.
     * This class has:
     * all fields the first class
     * set methods for this all fields
     * method build; inside it we go to private constructor the first class, this method return
     * object, which is the type of the first class
     */
    public static class DepartmentBuilder {
        private String name;
        private List<Employee> employees;

        public DepartmentBuilder setName(String name) {
            if (name == null) {
                System.out.println("Name of department can not be NULL");
                name = "";
                System.out.println("Department's name has been installed such as empty" );
            }
            this.name = name;
            return this;
        }


        public DepartmentBuilder setDepartment(List<Employee> employees) {

            if (employees == null) {

                System.out.println("Employee has ERRORS");
                employees = new LinkedList<Employee>();
                Post p = new Post.PostBuilder().setName("").setSalary(0).build();
                Employee emp = new Employee.EmployeeBuilder().setName("").
                        setSurname("").
                        setFathername("").
                        setAddress("").
                        setBeingInHospital(false).
                        setBeingInPoliclinic(false).
                        setCategory(CategoryDepartment.NONE).
                        setDateComingAtWork(LocalDateTime.now()).
                        setPost(p).
                        setWorkingRate(0).
                        setYearBorn(LocalDateTime.now()).
                        build();
                employees.add(emp);
                System.out.println("Employee has been installed such as empty" );
            }
                this.employees = employees;
                return this;

            }
            public Department build () {
                return new Department(this);
            }
        }

    }
    //public boolean addEmployee(Employee employee)

    /*public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }*/
     /*public void setName(String name) {
        this.name = name;
    }*/





