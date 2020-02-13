package com.celestino.model.util;

public class ClinicException extends Exception {

    public ClinicException() {

    }

    public ClinicException(String message) {
        super(message);
    }

    public ClinicException(Throwable message) {
        super(message);

    }

    public ClinicException(String message, Throwable message1){
        super(message, message1);
    }

}
