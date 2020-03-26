package com.thoughtworks;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        while (true) {
            System.out.format("1. 注册\n" + "2. 登录\n" + "3. 退出\n" + "请输入你的选择(1~3)：");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            if (input == 1) {
                Prompt prompt = new Register();
                prompt.inputInformation();
                while (true) {
                    String inputInformation = scanner.next();
                    String[] registerInformatics = inputInformation.split(",");

                    String checkFormat = CheckUtil.checkFormat(input, registerInformatics);
                    if (!checkFormat.equals("")) {
                        prompt.errorInformation(checkFormat);
                        continue;
                    }

                    User inputUser = new User(registerInformatics[0], registerInformatics[1], registerInformatics[2], registerInformatics[3]);
                    String checkResult = CheckUtil.checkFormation(inputUser);
                    UserRepository userRepository = new UserRepository();
                    if (checkResult.equals("")) {
                        if (userRepository.save(inputUser)) {
                            prompt.rightInformation(inputUser);
                        }
                    } else {
                        prompt.parameterError(checkResult);
                        continue;
                    }
                    userRepository.closeConnection();
                    break;
                }
                continue;
            }
            if (input == 2) {
                Login prompt = new Login();
                prompt.inputInformation();
                int inputCount = 0;

                while (true) {
                    String inputInformation = scanner.next();
                    String[] loginInformations = inputInformation.split(",");

                    UserRepository userRepository = new UserRepository();
                    if (userRepository.queryLockStatementByname(loginInformations[0])) {
                        prompt.locked();
                        continue;
                    }

                    inputCount++;

                    String checkFormat = CheckUtil.checkFormat(input, loginInformations);
                    if (!checkFormat.equals("")) {
                        prompt.errorInformation(checkFormat);
                        continue;
                    }


                    User user = userRepository.queryByname(loginInformations[0]);
                    String checkResult = CheckUtil.isLoginRight(loginInformations[0], loginInformations[1], user);
                    if (checkResult.equals("")) {
                        prompt.rightInformation(user);
                    } else {
                        if (inputCount >= 3) {
                            userRepository.update(loginInformations[0], 1);
                            prompt.locked();
                            continue;
                        }
                        prompt.parameterError(checkResult);
                        continue;
                    }
                    userRepository.closeConnection();
                    break;
                }
            }
        }
    }

}
