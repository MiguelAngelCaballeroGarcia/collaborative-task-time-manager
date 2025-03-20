package edu.ub.pis2324.authenticationexample.domain;

import java.util.regex.Pattern;

import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidPasswordException;

public class Password {

    /* Atributos */
    public String password;
    public final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    /* Constructor */
    public Password(String password) throws InvalidPasswordException {
        if (Pattern.matches(PASSWORD_PATTERN, password)) {
            this.password = password;
        } else {
            throw new InvalidPasswordException();
        }
    }
}
