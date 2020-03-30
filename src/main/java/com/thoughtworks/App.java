package com.thoughtworks;

import java.util.Scanner;

public class App {
    private static final String MAIN_SURFACE = "1. 注册\n2. 登录\n3. 退出\n请输入你的选择(1~3)：";

    public static void main(String[] args) {
        boolean isLoop = true;

        while (isLoop) {
            System.out.println(MAIN_SURFACE);

            Scanner scanner = new Scanner(System.in);
            String i = scanner.next();
            switch (i) {
                case "1":
                    Register register = new Register();
                    register.show();
                    break;
                case "2":
                    Login login = new Login();
                    login.show();
                    break;
                case "3":
                    System.out.println("已退出");
                    isLoop = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }

}
