package com.example.shippermanager.loggin;

public interface LoginInContract {
    interface View{
        void loginInSuccess();
        void loginInFailure(String error);
    }

    interface Presenter{
        void handleSingIn(String userName,String password);
    }
}
