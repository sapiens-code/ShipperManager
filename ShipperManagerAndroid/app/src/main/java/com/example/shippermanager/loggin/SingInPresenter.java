package com.example.shippermanager.loggin;

public class SingInPresenter implements LoginInContract.Presenter {


    private LoginInContract.View View;

    public void setView(LoginInContract.View view)
    {
        View = view;
    }

    @Override
    public void handleSingIn(String userName, String password) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("profiles");

        Query phoneQuery = ref.orderByChild(phoneNo).equalTo("+923336091371");
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
        if(userName.equals("lamminhhy")&&password.equals("1234"))
        {
            View.loginInSuccess();
            return;
        }
        View.loginInFailure("Username or Password not true");
    }
}
