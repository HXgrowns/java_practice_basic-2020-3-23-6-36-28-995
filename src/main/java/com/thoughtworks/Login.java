package com.thoughtworks;

import java.util.Scanner;

public class Login {

    public void show() {
        inputInformation();

        Scanner scanner = new Scanner(System.in);
        String[] loginInfos = scanner.next().trim().split(",");

        if (!checkFormat(loginInfos)) {
            System.out.println("格式错误\n请按正确格式输入用户名和密码：");
            show();
        }

        UserRepository userRepository = new UserRepository();
        int count = userRepository.queryCountByname((loginInfos[0]));

        if (!checkParameter(loginInfos, userRepository)) {
            count++;
            userRepository.updateCount(loginInfos[0], count);
            if (count < 3) {
                show();
            }
            userRepository.updateLockStatement(loginInfos[0], 1);
            lockUser();
            System.out.println("1111111111");
            return;

        }

        if (userRepository.queryLockStatementByname(loginInfos[0]) == 1) {
            lockUser();
            System.out.println("222222222");
            return;
        }

        User user = userRepository.queryByname(loginInfos[0]);
        rightInformation(user);
    }

    private void inputInformation() {
        System.out.println("请输入用户名和密码(格式：用户名,密码)：");
    }

    private boolean checkFormat(String[] loginInfos) {
        if (loginInfos != null && loginInfos.length == 2) {
            if (!CheckUtil.isRightUsername(loginInfos[0])) {
                System.out.println("用户名不合法\n请输入正确的用户名");
                return false;
            }

            if (!CheckUtil.isRightPassword(loginInfos[1])) {
                System.out.println("密码不合法\n请输入正确的密码");
                return false;
            }
            return true;
        }

        return false;
    }

    private boolean checkParameter(String[] loginInfos, UserRepository userRepository) {
        String password = userRepository.queryPasswordByname(loginInfos[0]);
        if (password != null && password.equals(loginInfos[1])) {
            return true;
        }

        System.out.println("密码或用户名错误\n请重新输入用户名和密码：");
        return false;
    }

    private void lockUser() {
        System.out.println("您已3次输错密码，账号被锁定");
    }


    private void rightInformation(User user) {
        System.out.format("%s，欢迎回来！", user.getName());
        System.out.format("您的手机号是%s，邮箱是%s\n", user.getPhoneNumber(), user.getEmail());
    }

}
