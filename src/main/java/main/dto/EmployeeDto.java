package main.dto;

import java.time.LocalDate;

public class EmployeeDto {
    private int idEmployee;
    private String myPost;
    private LocalDate dateComingAtWork;
    private double workingRate;
    private String category;
    private String surname;
    private String name;
    private String fathername;
    private String address;
    private LocalDate yearBorn;
    private String login;

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getMyPost() {
        return myPost;
    }

    public LocalDate getDateComingAtWork() {
        return dateComingAtWork;
    }

    public double getWorkingRate() {
        return workingRate;
    }

    public String getCategory() {
        return category;
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

    public String getLogin() {
        return login;
    }

    public EmployeeDto setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
        return this;
    }

    public EmployeeDto setMyPost(String myPost) {
        this.myPost = myPost;
        return this;
    }

    public EmployeeDto setDateComingAtWork(LocalDate dateComingAtWork) {
        this.dateComingAtWork = dateComingAtWork;
        return this;
    }

    public EmployeeDto setWorkingRate(double workingRate) {
        this.workingRate = workingRate;
        return this;
    }

    public EmployeeDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public EmployeeDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public EmployeeDto setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeDto setFathername(String fathername) {
        this.fathername = fathername;
        return this;
    }

    public EmployeeDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public EmployeeDto setYearBorn(LocalDate yearBorn) {
        this.yearBorn = yearBorn;
        return this;
    }

    public EmployeeDto setLogin(String login) {
        this.login = login;
        return this;
    }
}
