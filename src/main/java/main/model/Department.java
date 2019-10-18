package main.model;


import main.enums.CategoryDepartment;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * Class Department has:
 * @Override methods: equals, toString and hashcode,
 * static Class DepartmentBuilder, which allows users to set values of fields right.
 */
public class Department {
    private long id;
    private String name;

    @XmlElements({
            @XmlElement(name ="employee",type = Employee.class)
    })
    @XmlElementWrapper(name = "employees")
    private List<Employee> employees;
    private static int count;
    {id =++count;}
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

    /**
     * The next class alloys user to set values of fields correctly.
     * This class has:
     * all fields the first class
     * set methods for this all fields
     * method build; inside it we go to private constructor the first class, this method return
     * object, which is the type of the first class
     */
    public static class DepartmentBuilder{
        private String name;
        private List<Employee> employees;
        public DepartmentBuilder setName (String name){
            //try {
                if(name==null){
//                    throw new NullPointerException();
//                }
//            }
//            catch (NullPointerException e){
//                System.out.println("name is NULL");
//                e.printStackTrace();
                name="";
           }
//            catch (Exception e){
//                System.out.println("name has Errors");
//                e.printStackTrace();
//                name="";
//            }
//            finally {
              this.name=name;
               return this;

        }
        public DepartmentBuilder setDepartment(List<Employee> employees){
            try {
                if(employees==null){
                    throw new Exception();
                }
            }
            catch (Exception e){
                System.out.println("Employee has ERRORS");
                e.printStackTrace();
                employees=new LinkedList<Employee>();
                Post p=new Post.PostBuilder().setName("").setSalary(0).build();
                Employee emp=new Employee.EmployeeBuilder().setName("").
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
            }
            finally {

                this.employees=employees;
                return this;
            }
        }
        public Department build(){
            return new Department(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees);
    }

    @Override
    public String toString() {
        String res= "Department:" +
                "name='" + name + '\''+", employees:" ;
        for (Employee e : employees) {
            res+=e.toString() + "; ";
        }
                return res;
    }
    //public boolean addEmployee(Employee employee)

    /*public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }*/
     /*public void setName(String name) {
        this.name = name;
    }*/




}
