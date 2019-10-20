package main.model;

import main.exceptions.MaxSalaryException;
import main.exceptions.MinSalaryException;
import main.exceptions.SalaryException;

import java.util.Objects;

/** class Post has some information about Post.
 * It is name of Post and full salary which Employee can get when he or she has all working rate.
 * MAX_SALARY is private const. It is Max salary.
 * MIN_SALARY is private const. It is Min Salary.
 * Id alloys determine every post Unique.
 * Count is private static change, which alloys calculate Id for every new post
 *
 * Class Post has:
 * private constructors,
 * get Functions for all Its fields,
 * @Override methods: equals, toString and hashcode,
 * static Class PostBuilder, which allows users to set values of fields right.
 */
public class Post {


    private long id;
    private String name;
    private double salary;

    private final double MAX_SALARY =5000;
    private final double MIN_SALARY =3000;

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

        private String name;
        private double salary;

        private final double MAX_SALARY =5000;
        private final double MIN_SALARY =3000;

        public PostBuilder setName(String name){

                if (name == null)
                {name="";}
                this.name=name;
                return this;
        }

        public PostBuilder setSalary(double salary) /*throws SalaryException */{

                if(salary<MIN_SALARY){
                   salary=MIN_SALARY;
                    System.out.println("salary can not be less than minimum salary");
                }
                if (salary> MAX_SALARY){
                    salary= MAX_SALARY;
                    System.out.println("salary can not be more than maximum salary");
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



