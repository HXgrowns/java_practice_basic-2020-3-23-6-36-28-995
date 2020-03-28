package com.thoughtworks;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        boolean isLoop = true;

        while (isLoop) {
            MainInterface mainInterface = new MainInterface();
            mainInterface.show();

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

//huxiao,18890061083,182930@qq.com,18293013847xiao
//aaa,18890061083,804156017@qq.com,19930706xiao
//bbb,18890061083,804156017@qq.com,19930706xiao
//ccc,18890061083,804156017@qq.com,19930706xiao
//ddd,18890061083,804156017@qq.com,19930706xiao
//eee,18890061083,804156017@qq.com,19930706xiao

//aaa,199306xiao
//bbb,19930706xiao
//eee,19930706xiao