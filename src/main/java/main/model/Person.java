package main.model;

import java.time.LocalDateTime;

import java.util.Objects;

/**

 * Class Person has:
 * Default and private constructors,
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
            this.surname=personBuilder.surname;
            this.name=personBuilder.name;
            this.fathername=personBuilder.fathername;
            this.address=personBuilder.address;
            this.yearBorn=personBuilder.yearBorn;
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

                 if (surname==null) {
                     surname="";
                     System.out.println("Surname can not be null");
                     System.out.println("Surname has been installed such as empty" );
                 }
                 this.surname = surname;
                 return this;
         }

         public PersonBuilder setName(String name){
             if (name==null) {
                 name="";
                 System.out.println("Name can not be null");
                 System.out.println("name has been installed such as empty" );
             }
             this.name = name;
             return this;
         }

         public PersonBuilder setFathername(String fathername){
             if (fathername==null) {
                 fathername="";
                 System.out.println("Fathername can not be null");
                 System.out.println("Fathername has been installed such as empty" );
             }
             this.fathername = fathername;
             return this;
         }

         public PersonBuilder setAddress(String address){
             if (address==null) {
                 address="";
                 System.out.println("Address can not be null");
                 System.out.println("Address has been installed such as empty" );
             }
             this.address = address;
             return this;
         }

         public PersonBuilder setYearBorn(LocalDateTime yearBorn){
             if (yearBorn==null) {
                 yearBorn=LocalDateTime.now();
                 System.out.println("Year Born can not be null");
                 System.out.println("Year Born has been installed such as Date Time which is now" );
             }
             this.yearBorn= yearBorn;
             return this;
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
