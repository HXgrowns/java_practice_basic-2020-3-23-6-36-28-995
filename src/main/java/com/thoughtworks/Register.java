package com.thoughtworks;

import java.util.Scanner;

public class Register {
    private final String INPUT_INFOMATION = "请输入注册信息(格式：用户名，手机号，邮箱，密码)";
    private final String FORMAT_ERROR = "格式错误\n请按正确格式输入注册信息：";
    private final String USER_ERROR = "用户名不合法\n请输入合法的注册信息：";
    private final String PHONE_ERROR = "手机号不合法\n请输入合法的注册信息：";
    private final String EMAIL_ERROR = "邮箱不合法\n请输入合法的注册信息：";
    private final String PASSWORD_ERROR = "密码不合法\n请输入合法的注册信息：";

    public void show() {
        while (true) {
            inputInformation();

            Scanner scanner = new Scanner(System.in);
            String[] registerInfos = scanner.next().trim().split(",");
            if (!checkFormat(registerInfos)) {
                printErrorInformation();
                continue;
            }

            User user = checkParameter(registerInfos);
            if (user == null) {
                continue;
            }

            UserRepository userRepository = new UserRepository();
            boolean isSaveSuccess = userRepository.save(user);

            if (!isSaveSuccess) {
                continue;
            }

            userRepository.closeConnection();
            rightInformation(registerInfos[0]);
            break;
        }
    }

    private void inputInformation() {
        System.out.println(INPUT_INFOMATION);
    }

    private void printErrorInformation() {
        System.out.println(FORMAT_ERROR);
    }

    private void rightInformation(String name) {
        System.out.format("%s，恭喜你注册成功！\n", name);
    }

    private boolean checkFormat(String[] registerInfos) {
        return registerInfos != null && registerInfos.length == 4;
    }

    private User checkParameter(String[] registerInfos) {
        if (!CheckUtil.isRightUsername(registerInfos[0])) {
            System.out.println(USER_ERROR);
            return null;
        }
        if (!CheckUtil.isRightPhone(registerInfos[1])) {
            System.out.println(PHONE_ERROR);
            return null;
        }
        if (!CheckUtil.isRightEmail(registerInfos[2])) {
            System.out.println(EMAIL_ERROR);
            return null;
        }
        if (!CheckUtil.isRightPassword(registerInfos[3])) {
            System.out.println(PASSWORD_ERROR);
            return null;
        }
        return new User(registerInfos[0], registerInfos[1], registerInfos[2], registerInfos[3]);
    }


}
