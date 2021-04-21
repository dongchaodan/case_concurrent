package com.aop;

public class TestDemoAop {

    public static void main(String[] args) {
        ProxyHandler handler = new ProxyHandler();
        UserManager userManager = (UserManager) handler.newProxyInstance(new UserManagerImpl());
        userManager.addUser("001","于亮");
    }
}
