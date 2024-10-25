package com.personalproject.carloan.services.exceptions;

public class OutOfWorkingHoursException extends RuntimeException{
    public OutOfWorkingHoursException(String message){
        super(message);
    }

}
