package com.thoughtworks;

public class CheckUtil {

    public static String checkFormat(int selectedFormat, String[] inputInformations) {
        StringBuilder errorInformation = new StringBuilder();
        if (selectedFormat == 1 && inputInformations.length != 4) {
            errorInformation.append("请按正确格式输入注册信息");
        }
        if (selectedFormat == 2 && inputInformations.length != 2) {
            errorInformation.append("请按正确格式输入用户名和密码：");
        }
        return errorInformation.toString();
    }

    public static String checkFormation(User inputUser) {
        StringBuilder errorInformation = new StringBuilder();
        if (!isRightUsername(inputUser.getName())) {
            errorInformation.append("用户名不合法");
        }
        if (!isRightPhone(inputUser.getPhoneNumber())) {
            errorInformation.append("手机号不合法");
        }
        if (!isRightEmail(inputUser.getEmail())) {
            errorInformation.append("邮箱不合法");
        }
        if (!isRightPassword(inputUser.getPassword())) {
            errorInformation.append("密码不合法");
        }

        return errorInformation.toString();
    }

    public static boolean isRightUsername(String name) {
        return name != null && name.length() >= 2 && name.length() <= 10;
    }

    public static boolean isRightPhone(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.charAt(0) != '1' || phoneNumber.length() != 11) {
            return false;
        }
        for (int index = 1; index < phoneNumber.length(); index++) {
            if (phoneNumber.charAt(index) < '0' || phoneNumber.charAt(index) > '9') {
                return false;
            }
        }
        return true;
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

    public static String isLoginRight(String userName, String password, User user) {
        StringBuilder errorInformation = new StringBuilder();
        if (user != null && userName != null && userName.equals(user.getName()) && password != null && password.equals(user.getPassword())) {
            return "";
        }
        errorInformation.append("密码或用户名错误");
        return errorInformation.toString();
    }
}
