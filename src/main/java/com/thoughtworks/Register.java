package com.thoughtworks;

import java.util.Scanner;

public class Register {

    public void show() {
        inputInformation();

        Scanner scanner = new Scanner(System.in);
        String[] registerInfos = scanner.next().trim().split(",");
        if (!checkFormat(registerInfos)) {
            errorInformation();
            show();
        }

        if (!checkParameter(registerInfos)) {
            show();
        }

        UserRepository userRepository = new UserRepository();
        boolean isSaveSuccess = userRepository.save(new User(registerInfos[0], registerInfos[1], registerInfos[2], registerInfos[3]));
        if (!isSaveSuccess) {
            return;
        }
        userRepository.closeConnection();
        rightInformation(registerInfos[0]);
    }

    private void inputInformation() {
        System.out.println("请输入注册信息(格式：用户名，手机号，邮箱，密码)");
    }

    private void errorInformation() {
        System.out.println("格式错误\n请按正确格式输入注册信息：");
    }

    private void rightInformation(String name) {
        System.out.format("%s，恭喜你注册成功！\n", name);
    }

    private boolean checkFormat(String[] registerInfos) {
        return registerInfos != null && registerInfos.length == 4;
    }

    private boolean checkParameter(String[] registerInfos) {
        if (!CheckUtil.isRightUsername(registerInfos[0])) {
            System.out.println("用户名不合法\n请输入合法的注册信息：");
            return false;
        }
        if (!CheckUtil.isRightPhone(registerInfos[1])) {
            System.out.println("手机号不合法\n请输入合法的注册信息：");
            return false;
        }
        if (!CheckUtil.isRightEmail(registerInfos[2])) {
            System.out.println("邮箱不合法\n请输入合法的注册信息：");
            return false;
        }
        if (!CheckUtil.isRightPassword(registerInfos[3])) {
            System.out.println("密码不合法\n请输入合法的注册信息：");
            return false;
        }
        return true;
    }


}
