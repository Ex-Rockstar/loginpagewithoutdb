package com.example.loginpage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword; private
    Button buttonLogin;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME  = "AuthPrefs";
    private static final String KEY_USERNAME = "username"; private
    static final String KEY_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword); buttonLogin =
                findViewById(R.id.buttonLogin);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE); saveCredentials("user",
                "password");
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void saveCredentials(String username, String password) { SharedPreferences.Editor
            editor = sharedPreferences.edit(); editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }
    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String  password = editTextPassword.getText().toString().trim();
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, ""); String
                savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
        if (username.equals(savedUsername) && password.equals(savedPassword)) {
// Successful login
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show(); Intent
                    intent = new Intent(MainActivity.this, HomeActivity.class); startActivity(intent);

            finish();
        } else {
// Invalid credentials
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}