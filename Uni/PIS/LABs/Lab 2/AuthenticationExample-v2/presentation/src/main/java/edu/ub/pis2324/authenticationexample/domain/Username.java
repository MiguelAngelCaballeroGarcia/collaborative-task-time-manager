package edu.ub.pis2324.authenticationexample.domain;

import java.util.regex.Pattern;

import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidUsernameException;

public class Username {
    /* Atributos */
    public String username;
    public final static String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,16}$";

    /* Constructor */
    public Username(String username) throws InvalidUsernameException {
        if(Pattern.matches(USERNAME_PATTERN, username)){
            this.username = username;
        } else {
            throw new InvalidUsernameException();
        }
    }
}
