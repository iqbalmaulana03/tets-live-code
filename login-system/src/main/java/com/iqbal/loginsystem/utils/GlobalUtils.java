package com.iqbal.loginsystem.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GlobalUtils {

    private GlobalUtils() {
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@]{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUsername(String email) {
        String regex = "^[a-zA-Z0-9._-]{3,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidFullName(String fullName){
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }
}
