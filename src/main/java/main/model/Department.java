package main.model;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * Class Department has:
 * @Override methods: equals, toString and hashcode,
 * static Class DepartmentBuilder, which allows users to set values of fields right.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    private int id;

    @NotNull(message = "name can not be null")
    private String name;

    @XmlElements({
            @XmlElement(name ="employee",type = Employee.class)
    })
    @XmlElementWrapper(name = "employees")

    private List<Employee> employees;

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private static int count;
    {
        id =++count;}



    private Department(DepartmentBuilder departmentBuilder){
        this.name=departmentBuilder.name;
        //this.employees=departmentBuilder.employees;
        this.id =departmentBuilder.idDepartment;
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
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
                "name=" + name;// + ' ' + ", employees:";
//        for (Employee e : employees) {
//            res += e.toString() + "; ";
//        }
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
        private int idDepartment;
       // private List<Employee> employees;

        public DepartmentBuilder setName(String name) {
            if (name == null) {
                System.out.println("Name of department can not be NULL");
                name = "";
                System.out.println("Department's name has been installed such as empty" );
            }
            this.name = name;
            return this;
        }

        public DepartmentBuilder setIdDepartment(int idDepartment) {
            this.idDepartment = idDepartment;
            return this;
        }

       // public DepartmentBuilder setEmployees(List<Employee> employees) {

        //    if (employees == null) {

                //System.out.println("Employee has ERRORS");
                //employees = new LinkedList<Employee>();
          //      Post p = new Post.PostBuilder().setName("").setSalary(0).build();
//                Employee emp = new Employee.EmployeeBuilder().setName("").
//                        setSurname("").
//                        setFathername("").
//                        setAddress("").
//                        setBeingInHospital(false).
//                        setBeingInPoliclinic(false).
//                        setCategory(null).
//                        setDateComingAtWork(LocalDate.now()).
//                        setPost(p).
//                        setWorkingRate(0).
//                        setYearBorn(LocalDate.now()).
//                        build();
//                employees.add(emp);
//                System.out.println("Employee has been installed such as empty" );
 //           }
                //this.employees = employees;
            //    return this;

            //}
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





