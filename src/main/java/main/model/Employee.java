package main.model;

import main.enums.CategoryDepartment;
import main.exceptions.WorkingRate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.enums.CategoryDepartment.NONE;

/**
 * class Employee has some information about  Employee.
 * It inheritances from Person  surname, name, fathername, address, yearBorn  and all Its methods.
 * Id alloys determine every person Unique.
 * Count is private static change, which alloys calculate Id for every new person
 *
 * Class Employee has:
 * private constructor,
 * get Functions for all Its fields,
 * @Override methods: equals, toString and hashcode,
 * static Class EmployeeBuilder, which allows users to set values of fields right.
 */
public class Employee extends Person {
    private long idEmployee;
    private Department department;
    private Post myPost;
    private LocalDateTime dateComingAtWork;
    private double workingRate;
    private CategoryDepartment category;
    private boolean beingInPoliclinic;
    private boolean beingBeingInHospital;

    private static int count;

    /**
     *
     * @param employeeBuilder is type of EmployeeBuilder.
     * It alloys us to set for all fields our object Employee some values.
     */
    private Employee(EmployeeBuilder employeeBuilder){
        try {
            if(employeeBuilder==null){
                throw new NullPointerException();
            }
        }
        catch (Exception e){
            employeeBuilder=new EmployeeBuilder();
            System.out.println("Object of class EmployeeBuilder has Errors");
            employeeBuilder.department=new Department.DepartmentBuilder().
                    setName("").
                    setDepartment(null).build();
            myPost=employeeBuilder.myPost;
            dateComingAtWork=employeeBuilder.dateComingAtWork;
            workingRate=employeeBuilder.workingRate;
            category=employeeBuilder.category;
            beingInPoliclinic=employeeBuilder.beingInPoliclinic;
            beingBeingInHospital=employeeBuilder.beingBeingInHospital;
            surname=employeeBuilder.surname;
            name=employeeBuilder.name;
            fathername=employeeBuilder.fathername;
            address=employeeBuilder.address;
            yearBorn=employeeBuilder.yearBorn;
        }
        finally {

            this.department=employeeBuilder.department;
            this.myPost=employeeBuilder.myPost;
            this.dateComingAtWork=employeeBuilder.dateComingAtWork;
            this.workingRate=employeeBuilder.workingRate;
            this.category=employeeBuilder.category;
            this.beingInPoliclinic=employeeBuilder.beingInPoliclinic;
            this.beingBeingInHospital=employeeBuilder.beingBeingInHospital;
            this.surname=employeeBuilder.surname;
            this.name=employeeBuilder.name;
            this.fathername=employeeBuilder.fathername;
            this.address=employeeBuilder.address;
            this.yearBorn=employeeBuilder.yearBorn;
        }
    }


    /**
     * With the help of the next action we can instant some unique Id
     */
    {idEmployee=++count;}

    public boolean getBeingInPoliclinic(){
        return beingInPoliclinic;
    }

    public boolean getBeingInHospital(){
        return beingBeingInHospital;
    }

    public long getIdEmployee() {
        return idEmployee;
    }
    public Department getDepartment() {
        return department;
    }

    public Post getMyPost() {
        return myPost;
    }
    public double getSalaryPost(){
        return myPost.getSalary();
    }

    public LocalDateTime getDateComingAtWork() {
        return dateComingAtWork;
    }

    public double getWorkingRate() {
        return workingRate;
    }

    public CategoryDepartment getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return this.getName() == employee.getName()&&
                this.getSurname()==employee.getSurname()&&
                this.getDepartment()==employee.getDepartment();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idEmployee, department, myPost, dateComingAtWork, workingRate, category);
    }
    @Override
    public String toString() {
        return "Employee:" +
                super.toString()+
                ", department=" + department +
                ", myPost=" + myPost +
                ", dateComingAtWork=" + dateComingAtWork +
                ", workingRate=" + workingRate +
                ", category='" + category + '\'' +
                '}';
    }

    /**
     * The next class alloys user to set values of fields correctly.
     * This class has:
     * all fields the first class
     * set methods for this all fields
     * method build; inside it we go to private constructor the first class, this method return
     * object, which is the type of the first class
     */
    public static class EmployeeBuilder{
        private Department department;
        private Post myPost;
        private LocalDateTime dateComingAtWork;
        private double workingRate;
        private CategoryDepartment category;
        private boolean beingInPoliclinic;
        private boolean beingBeingInHospital;
        private String surname;
        private String name;
        private String fathername;
        private String address;
        private LocalDateTime yearBorn;

        public EmployeeBuilder setDepartment(Department department) {
            try {
                if (department==null){
                    throw new NullPointerException();
                }
            }
            catch (Exception e){
                System.out.println("department has ERRORS");
                e.printStackTrace();
                department=new Department.DepartmentBuilder().setDepartment(null).setName(null).build();
            }
            this.department = department;
            return this;
        }
        public EmployeeBuilder setPost(Post post){
            try {
                if(post==null){
                    throw new NullPointerException("Post needs to be initialised");
                }
            }
            catch (NullPointerException e){
                System.out.println("Warning: "+e.getMessage());
                post=new Post.PostBuilder().setName(null).setSalary(0).build();
            }
            finally {
                this.myPost=post;
                return this;
            }

        }
        public EmployeeBuilder setDateComingAtWork(LocalDateTime localDateTime){
            try {
                if(localDateTime==null){
                    throw new  NullPointerException("Date coming at work can not be NULL");
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
                e.getMessage();
                localDateTime=LocalDateTime.now();
            }
            finally {
                this.dateComingAtWork=localDateTime;
                return this;
            }
        }
        public EmployeeBuilder setWorkingRate(double workingRate){
            try{
                if(workingRate>1. || workingRate<0.){
                    throw new WorkingRate("Working rate needs to be more 0 and less 1");
                }
            }
            catch (WorkingRate e){
                e.getMessage();
                System.out.println("Working rate equals 0");
                workingRate=0.;
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Working rate equals 0");
                workingRate=0.;
            }
            finally {
                this.workingRate=workingRate;
                return this;
            }
        }
        public EmployeeBuilder setCategory(CategoryDepartment category){
            try {
                if(category==null){
                    throw new NullPointerException("category can not be NULL");
                }
            }
            catch (NullPointerException e){
                e.getMessage();
                System.out.println("category is NONE");
                category=NONE;
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("category is NONE");
                category=NONE;
            }
             finally {
                this.category=category;
                return this;
            }
        }
        public EmployeeBuilder setBeingInPoliclinic(boolean beingInPoliclinic) throws Exception {
            try {
                throw new Exception("beingInPoliclinic has ERRORS");
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("beingInPoliclinic equals FALSE");
                beingInPoliclinic=false;
            }
            finally {
                this.beingInPoliclinic=beingInPoliclinic;
                return this;
            }
        }
        public EmployeeBuilder setBeingInHospital(boolean beingBeingInHospital) throws Exception{
            try {
                throw new Exception("beingInHospital has ERRORS");
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("beingInHospital equals FALSE");
                beingBeingInHospital=false;
            }
            finally {
                this.beingBeingInHospital=beingBeingInHospital;
                return this;
            }
        }


        public EmployeeBuilder setSurname(String surname) {
            try {
                if (surname==null){
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException e){
                System.out.println("Surname is NULL");
                surname="";
                e.printStackTrace();
            }
            catch (Exception e){
                System.out.println("Surname is not founded");
                surname="";
                e.printStackTrace();
            }
            finally {
                this.surname = surname;
                return this;
            }
        }

        public EmployeeBuilder setName(String name){
            try {
                if (name==null){
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException e){
                System.out.println("Name is NULL");
                name="";
                e.printStackTrace();
            }
            catch (Exception e){
                System.out.println("Name is not founded");
                name="";
                e.printStackTrace();
            }
            finally {
                this.name = name;
                return this;
            }
        }

        public EmployeeBuilder setFathername(String fathername){
            try {
                if (fathername==null){
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException e){
                System.out.println("Fathername is NULL");
                fathername="";
                e.printStackTrace();
            }
            catch (Exception e){
                System.out.println("Fathername is not founded");
                fathername="";
                e.printStackTrace();
            }
            finally {
                this.fathername = fathername;
                return this;
            }
        }

        public EmployeeBuilder setAddress(String address){
            try {
                if (address==null){
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException e){
                System.out.println("Address is NULL");
                address="";
                e.printStackTrace();
            }
            catch (Exception e){
                System.out.println("Address is not founded");
                address="";
                e.printStackTrace();
            }
            finally {
                this.address = address;
                return this;
            }
        }

        public EmployeeBuilder setYearBorn(LocalDateTime yearBorn){
            try {
                if (yearBorn==null){
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException e){
                System.out.println("YearBorn is NULL");
                yearBorn=LocalDateTime.now();
                e.printStackTrace();
            }
            catch (Exception e){
                System.out.println("YearBorn has ERROR");
                yearBorn=LocalDateTime.now();
                e.printStackTrace();
            }
            finally {
                this.yearBorn = yearBorn;
                return this;
            }
        }


        public Employee build(){
            return new Employee(this);
        }
        //Person p = new PersonBuilder().setAddress("jj").build();
        //Employee emp = new EmployeeBuilder().setName("p").setCategory(CategoryDepartment.NONE).build();
        /*public Employee(String surname, String name, String fathername, String address, LocalDateTime yearBorn, Department departmentName, Post myPost, LocalDateTime dateComingAtWork, float workingRate, CategoryDepartment category,boolean policlinic,boolean hospital) {
        super(surname, name, fathername, address, yearBorn);
        this.idEmployee = ++count;
        this.department=departmentName;
        this.myPost = myPost;
        this.dateComingAtWork = dateComingAtWork;
        this.workingRate = workingRate;
        this.category = category;
        this.beingInPoliclinic=policlinic;
        this.beingBeingInHospital=hospital;
    }*/

    }
}
