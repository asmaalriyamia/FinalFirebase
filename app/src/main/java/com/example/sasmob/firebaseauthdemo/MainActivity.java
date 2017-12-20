package com.example.sasmob.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{
    EditText userId, password;
    private  FirebaseAuth auth;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.edit_password);
        auth = FirebaseAuth.getInstance();

        //database = FirebaseDatabase.getInstance();
        //storage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    public void createNewUser(View view) {
        String user = userId.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "Provide User ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Provide Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.length() < 6) {
            Toast.makeText(this, "Password should be more than six charecter", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("it is registering...");
        progressDialog.show();


        auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful()){
                    String str = auth.getCurrentUser().getEmail();
                    Log.d("Current User is:",str);
                    progressDialog.dismiss();

                    Intent intent = new Intent(MainActivity.this,SignIn.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(View view) {

        Intent intent = new Intent(this,SignIn.class);
       startActivity(intent);

    }

    public void reset(View view) {

        Intent intent1 = new Intent(this,Rest_activity.class);
        startActivity(intent1);
    }

    public void delete(View view){
        startActivity(new Intent(this,Rest_activity.class));
    }
}
