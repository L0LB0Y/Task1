package com.example.task1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task1.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        binding.loginButton.setOnClickListener(x -> this.action());
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.inputUserName.getText().clear();
        binding.inputPassword.getText().clear();
        binding.inputUserName.requestFocus();
    }

    public void action() {
        String email = binding.inputUserName.getText().toString();
        String pass = binding.inputPassword.getText().toString();
        boolean check = inputValidation(email, pass);
        if (check) {
            progressDialog.setMessage("Loading !!!");
            progressDialog.show();
            authentication(email, pass);
        }
    }

    // Validation if the Failed is Empty or not
    private boolean inputValidation(String email, String pass) {
        if (email.isEmpty()) {
            binding.inputUserName.setError("Fill this Failed is Required!");
            binding.inputUserName.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputUserName.setError("Please Enter a Valid Email!");
            binding.inputUserName.requestFocus();
            return false;
        }
        if (pass.length() <= 5) {
            binding.inputPassword.setError("Password Required at less 5 Character");
            binding.inputPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void authentication(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                progressDialog.dismiss();
                startActivity(new Intent(MainActivity.this, Home.class));
            } else {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Incorrect Password OR Email !!", Toast.LENGTH_SHORT).show();
            }

        });

    }
}