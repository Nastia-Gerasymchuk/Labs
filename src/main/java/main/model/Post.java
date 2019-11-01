package main.model;

import main.exceptions.MaxSalaryException;
import main.exceptions.MinSalaryException;
import main.exceptions.SalaryException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Class Post has:
 * MAX_SALARY is private const. It is Max salary,
 * MIN_SALARY is private const. It is Min Salary,
 * @Override methods: equals, toString and hashcode,
 * static Class PostBuilder, which allows users to set values of fields right.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Post {
    private static final double MAX_SALARY =5000;
    private static final double MIN_SALARY =3000;

    private long id;

    @NotNull(message = "name can not be NULL")
    private String name;

    @Min(value = 3000,message = "salary needs to be more than 3000")
    @Max(value = 5000,message = "salary needs to be lass than 3000")
    private double salary;
    private static int count; // UUID

    {id=++count;}

    /**
     *private constructor
     * @param p is Object type PostBuilder
     */

    private Post(PostBuilder p){
            this.name=p.name;
            this.salary=p.salary;
    }

    private long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return name == post.name ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }

    @Override
    public String toString() {
        return "Post:" +
                "name='" + name + '\'' +
                ", salary=" + salary ;
    }

    /**
     * The next class alloys user to set values of fields correctly.
     * This class has:
     * all fields the first class
     * set methods for this all fields
     * method build; inside it we go to private constructor the first class, this method return
     * object, which is the type of the first class
     */
    public static class PostBuilder{


        private String name = "post";
        private double salary=MIN_SALARY;


        public PostBuilder setName(String name){

                if (name == null){
                name="";
                System.out.println("name can not be NULL\n");
                System.out.println("name has been installed such as empty" );
                }
                this.name=name;
                return this;
        }

        public PostBuilder setSalary(double salary) /*throws SalaryException */{

                if(salary<MIN_SALARY){
                   salary=MIN_SALARY;
                    System.out.println("salary can not be less than minimum salary");
                    System.out.println("salary has been installed such as MIN_SALARY" );
                }
                if (salary> MAX_SALARY){
                    salary= MAX_SALARY;
                    System.out.println("salary can not be more than maximum salary");
                    System.out.println("salary has been installed such as MAX_SALARY" );
                }
                this.salary = salary;
                return this;
            }


        public Post build(){
            return new Post(this);
        }
    }
}

    //    public Post setName(String name) {
//        this.name = name;
//        return this;
//    }

    //    public void setSalary(double salary) {
//        if(salary<=MIN_SALARY){
//            salary= MIN_SALARY;
//        }
//        if (salary> MAX_SALARY){
//            salary= MAX_SALARY;
//        }
//        this.salary = salary;
//    }



