package main.model;

import main.validation.ReviseAge;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**

 * Class Person has:
 * Default and private constructors,
 * @Override methods: equals, toString and hashcode,
 * static Class PersonBuilder, which allows users to set values of fields right.
 */

public  class Person {
    private int idPerson;

    @NotNull(message = "Surname can not be NULL")
    protected String surname;

    @NotNull(message = "Name can not be NULL")
    protected String name;

    @NotNull(message = "Fathername can not be NULL")
    protected String fathername;

    @NotNull(message = "Address can not be NULL")
    protected String address;

    @ReviseAge(min=21,message = "Person needs to have more than 21")
    protected LocalDate yearBorn;

    @NotNull
    protected String login;

    @NotNull
    protected String pasw;
    protected static int count;

    {
        idPerson =count++;}

    private Person(PersonBuilder personBuilder){
            this.surname=personBuilder.surname;
            this.name=personBuilder.name;
            this.fathername=personBuilder.fathername;
            this.address=personBuilder.address;
            this.yearBorn=personBuilder.yearBorn;
            this.login=personBuilder.login;
            this.pasw=personBuilder.pasw;
            this.idPerson=personBuilder.idPerson;
    }

   protected Person(){}

    protected long getPersonId() {
        return idPerson;
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

    public LocalDate getYearBorn() {
        return yearBorn;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public String getLogin() {
        return login;
    }

    public String getPasw() {
        return pasw;
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
        return Objects.hash(idPerson, surname, name, fathername, address, yearBorn);
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

    public void setIdPerson(int idPerson){
        this.idPerson=idPerson;
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
        private LocalDate yearBorn;
        private String login;
        private String pasw;
        private int idPerson;

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


         public PersonBuilder setYearBorn(LocalDate yearBorn){
             if (yearBorn==null) {
                 yearBorn=LocalDate.now();
                 System.out.println("Year Born can not be null");
                 System.out.println("Year Born has been installed such as Date Time which is now" );
             }
             this.yearBorn= yearBorn;
             return this;
         }

        public PersonBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public PersonBuilder setPasw(String pasw) {
            this.pasw = pasw;
            return this;
        }

        public PersonBuilder setIdPerson(int idPerson) {
            this.idPerson = idPerson;
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
     private Person setId(long idPerson) {
            this.idPerson = idPerson;
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
