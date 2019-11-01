package main.model;


import main.enums.CategoryDepartment;
import main.exceptions.MaxDoingHours;
import main.exceptions.MinDoingHours;

import java.time.LocalDateTime;


import static main.enums.CategoryDepartment.*;

public class Salary{
    private int id ;
    private Employee employee;
    private LocalDateTime dateOfSalary;
    private float workingHours;
    private static int count;
    private final int MAX_DOING_HOURS =248; //
    private final int MIN_DOING_HOURS =0;

    {id=count++;}

    /*public Salary(Employee employee, LocalDateTime dateOfSalary, float doingHours) {
        this.employee = employee;
        this.dateOfSalary = dateOfSalary;
        this.doingHours = doingHours;
    }*/
    public Salary(Employee employee, /*LocalDateTime dateOfSalary,*/ float doingHours) {
        setEmployee(employee);
        // now setDateOfSalary(dateOfSalary);
        setDateOfSalary(LocalDateTime.now());
        setDoingHours(doingHours);
    }
    ////?????????????????????????????????
    public Salary(){

    }

    @Override
    public String toString() {
        return "Salary:" +
                ", employee=" + employee.toString() +
                ", dateOfSalary=" + dateOfSalary +
                ", workingHours=" +workingHours ;
    }

    private int getId() {
        return id;
    }

    private Employee getEmployee() {
        return employee;
    }

    private void setEmployee(Employee employee) {

            if(employee==null) {
                System.out.println("employee is not founded");
                System.out.println("Employee exists faux");
                employee=new Employee.EmployeeBuilder().build();
            }
            this.employee = employee;
    }

    private LocalDateTime getDateOfSalary() {
        return dateOfSalary;
    }

    private void setDateOfSalary(LocalDateTime dateOfSalary) {

            if(dateOfSalary==null){
                System.out.println("date of salary can not be null");
                System.out.println("date of salary is now");
                dateOfSalary=LocalDateTime.now();
            }
            this.dateOfSalary=dateOfSalary;
    }

    private float getWorkingHours() {
        return workingHours ;
    }

    private void setDoingHours(float workingHours) {
        try {
            if (workingHours< MIN_DOING_HOURS){
                throw new MinDoingHours("Working days can not be less than MIN COUNT DOING HOURS: IT IS 0");
            }
            if (workingHours> MAX_DOING_HOURS){
                throw new MaxDoingHours("Working days can not be more than MAX COUNT DOING HOURS: IT IS 672");
            }
        } catch (MinDoingHours minDoingHours) {
            minDoingHours.getMessage();
            System.out.println("doing hours is MIN COUNT DOING HOURS: IT IS 0");
            workingHours=MIN_DOING_HOURS;
        }
        catch (MaxDoingHours maxDoingHours){
            maxDoingHours.getMessage();
            System.out.println("doing hours is MAX COUNT DOING HOURS: IT IS 672");
            workingHours=MAX_DOING_HOURS;
        }
        finally {

            this.workingHours = workingHours;
        }
    }

    private static int getCount() {
        return count;
    }
    private double AccrueSalary(){

        CategoryDepartment categoryDepartment=employee.getCategory();
        double persentOfCategory=GetPersentWithCategory(categoryDepartment);
        return (employee.getSalaryPost()*employee.getWorkingRate()*(1+persentOfCategory))/ getWorkingHours();
    }

    private double GetPersentWithCategory(CategoryDepartment categoryDepartment) {

            if(categoryDepartment==null){
                System.out.println(("Category can not be NULL"));
                System.out.println("Category is NONE");
                return 0;
            }
        if(categoryDepartment==UPPER)
            return 0.3;
        if (categoryDepartment==THE_FIRST)
            return 0.25;
        if(categoryDepartment==THE_SECOND)
            return 0.2;
        if (categoryDepartment==THE_FIRST)
            return 0.1;
        return 0;

    }


}
