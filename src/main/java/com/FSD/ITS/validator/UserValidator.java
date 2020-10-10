package com.FSD.ITS.validator;

import com.FSD.ITS.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    private List<String> errors;

    public boolean validateUser(User user) {
        errors = new ArrayList<>();
        if (null != user) {
            if (null != user.getFname()) {
                int fNameLen = user.getFname().trim().length();
                if (fNameLen != 0) {
                    if (fNameLen < 5 || fNameLen > 30)
                        errors.add("FName length should be between 5 to 30 Chars.");
                } else
                    errors.add("First Name can't be blank.");
            } else
                errors.add("First Name can't be null.");

            if (null != user.getlName()) {
                int lNameLen = user.getlName().trim().length();
                if (lNameLen > 0) {
                    if (lNameLen < 3 || lNameLen > 25)
                        errors.add("Last name length should be between 3 to 25 Chars.");
                }
                errors.add("Last Name can't be blank.");
            } else
                errors.add("Last Name can't be null.");


            if (null != user.getEmail()) {
                int emailLen = user.getEmail().trim().length();
                if (emailLen > 0) {
                    if (user.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
                        errors.add("Email:" + user.getEmail() + " is invalid.");
                } else
                    errors.add("email can't be blank.");
            } else
                errors.add("email can't be null.");


            if (null != user.getMobile()) {
                if (user.getMobile().matches("^\\d{10}$"))
                    errors.add("Mobile:" + user.getMobile() + " is invalid.");
            } else
                errors.add("mobile can't be null or blank.");

        } else
            errors.add("User can't be null");

        return !(errors.size() > 0);
    }

    public List<String> getErrors() {
        return errors;
    }


}
