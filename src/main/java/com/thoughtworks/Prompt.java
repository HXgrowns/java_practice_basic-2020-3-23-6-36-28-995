package com.thoughtworks;

public interface Prompt {
    void inputInformation();

    void errorInformation(String errorInformation);

    void rightInformation(User user);

    void parameterError(String errorInformation);
}
