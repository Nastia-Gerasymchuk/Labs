package main.model;

import java.time.LocalDateTime;

import java.util.Objects;

/**
 * class Person has some information about Person.
 * It is surname, name, fathername, address, yearBorn of Person.
 * Id alloys determine every person Unique.
 * Count is private static change, which alloys calculate Id for every new person
 *
 * Class Person has:
 * Default and private constructors,
 * get Functions for all Its fields,
 * @Override methods: equals, toString and hashcode,
 * static Class PersonBuilder, which allows users to set values of fields right.
 */
public  class Person {
    private long id;
    protected String surname;
    protected String name;
    protected String fathername;
    protected String address;
    protected LocalDateTime yearBorn;
    protected static int count;

    {id=count++;}

    private Person(PersonBuilder personBuilder){
        try {
            if (personBuilder==null){
                throw new NullPointerException();
            }
        }
        catch (NullPointerException e){
            System.out.println("Object PersonBuilder is NULL");
            personBuilder=new PersonBuilder();
            personBuilder.surname="";
            personBuilder.name="";
            personBuilder.fathername="";
            personBuilder.address="";
            personBuilder.yearBorn=LocalDateTime.now();

        }
        catch (Exception e){
            System.out.println("Object PersonBuilder has Error");
            personBuilder=new PersonBuilder();
            personBuilder.surname="";
            personBuilder.name="";
            personBuilder.fathername="";
            personBuilder.address="";
            personBuilder.yearBorn=LocalDateTime.now();
        }
        finally {
            this.surname=personBuilder.surname;
            this.name=personBuilder.name;
            this.fathername=personBuilder.fathername;
            this.address=personBuilder.address;
            this.yearBorn=personBuilder.yearBorn;
        }

    }

   protected Person(){}

    protected long getPersonId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFathername() {
        return fathername;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getYearBorn() {
        return yearBorn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(surname, person.surname)&&
                Objects.equals(name, person.name)&&
                Objects.equals(address, person.address);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, fathername, address, yearBorn);
    }

    @Override
    public String toString() {
        return "Person: " +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", fathername='" + fathername + '\'' +
                ", address='" + address + '\'' +
                ", yearBorn=" + yearBorn;

    }

    /**
     * The next class alloys user to set values of fields correctly.
     * This class has:
     * all fields the first class
     * set methods for this all fields
     * method build; inside it we go to private constructor the first class, this method return
     * object, which is the type of the first class
     */
     public static class PersonBuilder{
        private String surname;
        private String name;
        private String fathername;
        private String address;
        private LocalDateTime yearBorn;

         public PersonBuilder setSurname(String surname) {
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

         public PersonBuilder setName(String name){
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

         public PersonBuilder setFathername(String fathername){
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

         public PersonBuilder setAddress(String address){
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

         public PersonBuilder setYearBorn(LocalDateTime yearBorn){
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
         public Person build() {
             return new Person(this);
         }
    }
    /*public Person setName(String name) {
        this.name = name;
        return this;
    }*/
    /*public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }
     private Person setId(long id) {
            this.id = id;
           return this;
       }
       public Person setAddress(String address) {
        this.address = address;
        return this;
    }
     public Person setYearBorn(LocalDateTime yearBorn) {
        this.yearBorn = yearBorn;
        return this;
    }
     public Person setFathername(String fathername) {
        this.fathername = fathername;
        return this;
    }

    */


}