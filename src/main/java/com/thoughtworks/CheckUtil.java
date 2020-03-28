package com.thoughtworks;

public class CheckUtil {
    public static boolean isRightUsername(String name) {
        if (name == null) {
            return false;
        }
        return name.matches("^.{2,10}$");
    }

    public static boolean isRightPhone(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches("^1[0-9]{10}$");
    }

    public static boolean isRightEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@");
    }

    public static boolean isRightPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
    }
}
