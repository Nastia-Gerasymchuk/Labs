package main.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
    public static final double MAX_SALARY =5000;
    public static final double MIN_SALARY =3000;

    private int idPost;

    @NotNull(message = "name can not be NULL")
    private String name;

    @Min(value = 3000,message = "salary needs to be more than 3000")
    @Max(value = 5000,message = "salary needs to be less than 3000")
    private double salary;
    private int hoursPost;
    private static int count; // UUID

    {
        idPost =++count;}

    /**
     *private constructor
     * @param p is Object type PostBuilder
     */

    private Post(PostBuilder p){
            this.name=p.name;
            this.salary=p.salary;
            this.hoursPost=p.hoursPost;
            this.idPost=p.id;
    }

    public int getIdPost() {
        return idPost;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public int getHoursPost(){return hoursPost;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return name == post.name ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(idPost, name, salary,hoursPost);
    }

    @Override
    public String toString() {
        return "Post:" +
                "name='" + name + " " +
                ", salary=" + salary +
                ", hoursPost=" + hoursPost +
                '.' ;
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
        private int hoursPost=0;
        private double salary=MIN_SALARY;
        private int id;

        public PostBuilder setId(int id) {
            this.id = id;
            return this;
        }

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
        public PostBuilder setHoursPost(int hoursPost){

            if (hoursPost<0){
                hoursPost=0;
                System.out.println("hours Post can not be less then NULL\n");
                System.out.println("hours Post been installed such as 0" );
            }
            this.hoursPost=hoursPost;
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



