package edu.ub.pis2324.authenticationexample.domain;

import java.util.regex.Pattern;

import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidEmailException;

public class Email {

    /* Atributos */
    public String email;
    public final static String EMAIL_PATTERN = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z0-9]+\\.[a-z]{2,}$";

    /* Constructor */
    public Email(String email) throws InvalidEmailException {
        if(Pattern.matches(EMAIL_PATTERN, email)){
            this.email = email;
        } else {
            throw new InvalidEmailException();
        }
    }



}
