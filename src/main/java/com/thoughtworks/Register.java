package com.thoughtworks;

public class Register implements Prompt {

    @Override
    public void inputInformation() {
        System.out.println("请输入注册信息(格式：用户名，手机号，邮箱，密码)");
    }

    @Override
    public void errorInformation(String errorInformation) {
        System.out.println("格式错误\n" + errorInformation);
    }

    @Override
    public void rightInformation(User user) {
        System.out.format("%s，恭喜你注册成功！\n", user.getName());
    }

    @Override
    public void parameterError(String errorInformation) {
        System.out.println(errorInformation + "\n请输入合法的注册信息：");
    }

}
