package com.personalproject.carloan.services.exceptions;

public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException(String msg){
        super(msg);
    }
}
