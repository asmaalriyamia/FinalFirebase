package com.example.sasmob.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_page extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView userEmail;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(this,SignIn.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        userEmail = (TextView) findViewById(R.id.textView);
        userEmail.setText("Welcome" + user.getEmail());
        logout = (Button) findViewById(R.id.btn_logout);

        logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if ( view == logout){
            firebaseAuth.signOut();
            startActivity(new Intent(this,SignIn.class));
        }
    }
}
