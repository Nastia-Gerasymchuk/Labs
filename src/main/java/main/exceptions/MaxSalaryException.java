package main.exceptions;

public class MaxSalaryException extends SalaryException {
    public MaxSalaryException(){}

    public MaxSalaryException(String message){
        super(message);
    }
}
