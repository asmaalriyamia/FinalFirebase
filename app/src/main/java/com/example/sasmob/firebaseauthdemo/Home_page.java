package com.example.sasmob.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.sasmob.firebaseauthdemo.R.id.btn_logout;

public class Home_page extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView userEmail;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        if (userEmail == null) {
           // finish();
          // startActivity(new Intent(Home_page.this, SignIn.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        userEmail = (TextView) findViewById(R.id.textView);
        //userEmail.setText("Welcome" + user.getEmail());

        logout = (Button) findViewById(R.id.btn_logout);

        logout.setOnClickListener(this);

    }

    public void onLogout(View view) {
        if (this.logout != null) {
            FirebaseAuth.getInstance().signOut();

            startActivity(new Intent(Home_page.this, SignIn.class));

        }
    }

    @Override
    public void onClick(View v) {
       startActivity(new Intent(this,SignIn.class));
    }
}
