package com.thoughtworks;

public class Login implements Prompt {

    @Override
    public void inputInformation() {
        System.out.println("请输入用户名和密码(格式：用户名,密码)：");
    }

    @Override
    public void errorInformation(String errorInformation) {
        System.out.println("格式错误" + errorInformation);
    }

    @Override
    public void rightInformation(User user) {
        System.out.format("%s，欢迎回来！", user.getName());
        System.out.format("您的手机号是%s，邮箱是%s\n", user.getPhoneNumber(), user.getEmail());
    }

    @Override
    public void parameterError(String errorInformation) {
        System.out.println(errorInformation + "\n请重新输入用户名和密码：");
    }

    public void locked() {
        System.out.println("您已3次输错密码，账号被锁定");
    }
}
