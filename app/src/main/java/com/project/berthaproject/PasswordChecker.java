package com.project.berthaproject;

public class PasswordChecker {

    public static boolean Check(String username, String password) {
        return password.startsWith("p");
    }
}
