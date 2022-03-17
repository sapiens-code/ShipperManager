package com.example.shippermanager.loggin;

public class SingInPresenter implements LoginInContract.Presenter {


    private LoginInContract.View View;

    public void setView(LoginInContract.View view)
    {
        View = view;
    }

    @Override
    public void handleSingIn(String userName, String password) {

        if(userName.equals("lamminhhy")&&password.equals("1234"))
        {
            View.loginInSuccess();
            return;
        }
        View.loginInFailure("Username or Password not true");
    }
}
