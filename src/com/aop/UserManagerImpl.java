package com.aop;

public class UserManagerImpl  implements UserManager{

    @Override
    public void addUser(String userId, String userName) {
        try{
            System.out.println("UserManagerImpl.addUser() userId-->>" + userId);
        }catch(Exception e){
            e.printStackTrace();

            throw new RuntimeException();
        }
    }

    @Override
    public void delUser(String userId) {

    }

    @Override
    public void modifyUser(String userId, String userName) {

    }

    @Override
    public String findUser(String userId) {
        return null;
    }
}
