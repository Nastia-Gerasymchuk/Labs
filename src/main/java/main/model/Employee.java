package main.model;

import main.enums.CategoryDepartment;

import java.time.LocalDateTime;
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
    public static class EmployeeBuilder {
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

            if (department == null) {
                System.out.println("Department can not be NULL");
                department = new Department.DepartmentBuilder().setDepartment(null).setName(null).build();
            }
            this.department = department;
            return this;
        }

        public EmployeeBuilder setPost(Post post) {
            if (post == null) {
                System.out.println("Post can not be NULL");
                myPost = new Post.PostBuilder().setName("").setSalary(0).build();
            }
            this.myPost = myPost;
            return this;

        }

        public EmployeeBuilder setDateComingAtWork(LocalDateTime localDateTime) {
            if (localDateTime == null) {
                System.out.println("Date Coming At Work can not be NULL");
                localDateTime = LocalDateTime.now();
            }
            this.dateComingAtWork = localDateTime;
            return this;
        }

        public EmployeeBuilder setWorkingRate(double workingRate) {
            if (workingRate < 0.) {
                System.out.println("Working rate needs to be more 0");
                workingRate = 0;
            }
            this.workingRate = workingRate;
            return this;
        }

        public EmployeeBuilder setCategory(CategoryDepartment category) {

            if (category == null) {
                System.out.println("category can not be NULL");
                category = NONE;
            }
            this.category = category;
            return this;
        }



        public EmployeeBuilder setBeingInPoliclinic(boolean beingInPoliclinic) {
            this.beingInPoliclinic = beingInPoliclinic;
            return this;
        }

        public EmployeeBuilder setBeingInHospital(boolean beingBeingInHospital){

            this.beingBeingInHospital=beingBeingInHospital;
            return this;
        }

        public EmployeeBuilder setSurname(String surname) {

            if (surname==null){
                surname="";
                System.out.println("Surname can not be NULL");
            }

            this.surname = surname;
            return this;

        }

        public EmployeeBuilder setName(String name){
            if (name==null){
                name="";
                System.out.println("Name can not be NULL");
            }

            this.name = name;
            return this;
        }

        public EmployeeBuilder setFathername(String fathername){
            if (fathername==null){
                fathername="";
                System.out.println("Fathername can not be NULL");
            }

            this.fathername = fathername;
            return this;
        }

        public EmployeeBuilder setAddress(String address){
            if (address==null){
                address="";
                System.out.println("Address can not be NULL");
            }

            this.address = address;
            return this;
        }

        public EmployeeBuilder setYearBorn(LocalDateTime yearBorn){
            if (yearBorn==null) {
                yearBorn=LocalDateTime.now();
                System.out.println("Year Born can not be null");
            }
            this.yearBorn= yearBorn;
            return this;
        }
        public Employee build() {
            return new Employee(this);
        }

    }
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



