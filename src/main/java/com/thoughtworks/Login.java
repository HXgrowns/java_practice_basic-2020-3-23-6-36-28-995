package com.thoughtworks;

import java.util.Scanner;

public class Login {
    private final String INPUT_INFOMATION = "请输入用户名和密码(格式：用户名,密码)：";
    private final String LOCK_INFOMATION = "您已3次输错密码，账号被锁定";
    private final String FORMAT_ERROR = "格式错误\n请按正确格式输入用户名和密码：";
    private final String PARAMETER_ERROR = "密码或用户名错误\n请重新输入用户名和密码：";
    private final String USER_ERROR = "用户名不合法\n请输入正确的用户名";
    private final String PASSWORD_ERROR = "密码不合法\n请输入正确的密码";

    public void show() {
        while (true) {
            inputInformation();

            Scanner scanner = new Scanner(System.in);
            String[] loginInfos = scanner.next().trim().split(",");

            User user = checkFormat(loginInfos);
            if (user == null) {
                System.out.println(FORMAT_ERROR);
                continue;
            }
            UserRepository userRepository = new UserRepository();
            int count = userRepository.queryCountByName(user.getName());

            if (count >= 2) {
                lockUser();
                userRepository.closeConnection();
                break;
            }

            User loginUser = userRepository.loginByPasswordAndName(user.getName(), user.getPassword());
            if (loginUser == null) {
                System.out.println(PARAMETER_ERROR);
                count++;
                userRepository.updateCount(user.getName(), count);
                continue;
            }

            userRepository.updateCount(user.getName(), 0);
            rightInformation(loginUser);
            userRepository.closeConnection();
            break;
        }
    }

    private void inputInformation() {
        System.out.println(INPUT_INFOMATION);
    }

    private User checkFormat(String[] loginInfos) {
        if (loginInfos != null && loginInfos.length == 2) {
            if (!CheckUtil.isRightUsername(loginInfos[0])) {
                System.out.println(USER_ERROR);
                return null;
            }

            if (!CheckUtil.isRightPassword(loginInfos[1])) {
                System.out.println(PASSWORD_ERROR);
                return null;
            }
            return new User(loginInfos[0], null, null, loginInfos[1]);
        }

        return null;
    }

    private void lockUser() {
        System.out.println(LOCK_INFOMATION);
    }


    private void rightInformation(User user) {
        System.out.format("%s，欢迎回来！", user.getName());
        System.out.format("您的手机号是%s，邮箱是%s\n", user.getPhoneNumber(), user.getEmail());
    }

}
