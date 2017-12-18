package com.example.sasmob.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
         private Button btn_signin;
         private EditText edit_email;
         private EditText edit_password;
         private ProgressDialog progressDialog;
         private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        edit_email = (EditText) findViewById(R.id.email);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_signin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/firelog");
        DatabaseReference userREf = ref.child("users");
        Map<String , User> users = new HashMap<>();
        users.put("Asma", new User("April 1,1993","Asma Salim"));
        users.put("Hajer", new User("March 3,1993","Hajer Mohsin"));
        userREf.setValue(users);

    }
    public void SignIn(View view){
        String email = edit_email.getText().toString().trim();
        String passwords = edit_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Provide User ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwords)) {
            Toast.makeText(this, "Provide Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwords.length() < 6) {
            Toast.makeText(this, "Password should be more than six charecter", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("it is Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,passwords).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    Intent i = new Intent(SignIn.this,Home_page.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btn_signin) {
            startActivity(new Intent(this,Home_page.class));
        }
    }
}
