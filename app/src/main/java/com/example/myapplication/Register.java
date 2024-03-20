package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


   public static final String TAG= "TAG";
    EditText memail,mpassword,cpassword;
    Button registerButton;
    TextView loginBtn;
    ProgressBar progressBar;
    FirebaseFirestore  fstore;
    String userId;
    FirebaseAuth fAuth;

    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cpassword=findViewById(R.id.cpassword);
        memail = findViewById(R.id.email);
        mpassword=findViewById(R.id.mpassword);
        registerButton=findViewById(R.id.registerbtn);
        loginBtn=findViewById(R.id.goLogin);
        progressDialog = new ProgressDialog(this);


        fAuth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }

            private void PerforAuth() {
                final String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                final String cpass = cpassword.getText().toString().trim();


                if(!email.matches(emailPattern)){
                    memail.setError("enter context email");
                } else if (password.isEmpty() || password.length()<6) {
                    mpassword.setError("enter the proper password");

                } else if (!password.equals(cpassword)) {
                    cpassword.setError("password not match");

                }else {

                      progressDialog.setMessage("please wait while registration");
                      progressDialog.setTitle("registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseUser user = fAuth.getCurrentUser();
                                if (task.isSuccessful()) {
                                    DocumentReference df = fstore.collection("Users").document(user.getUid());
                                    Map<String,Object> userInfo = new HashMap<>();
                                    userInfo.put("Email",memail.getText().toString());
                                    // specify if user is admin

                                    userInfo.put("isUser",1);
                                    df.set(userInfo);

                                    progressDialog.dismiss();
                                    senduserToNextActivity();
                                    Toast.makeText(Register.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    progressDialog.dismiss();
                                    Toast.makeText(Register.this, ""+task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });


            }

    private void senduserToNextActivity() {
        Intent intent = new Intent(Register.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


}