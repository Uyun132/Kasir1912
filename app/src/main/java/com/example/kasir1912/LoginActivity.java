package com.example.kasir1912;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private String username,password;
    private EditText User,Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btLogin);
        User = findViewById(R.id.usernameLogin);
        Pass = findViewById(R.id.passwordLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = User.getText().toString();
                password = Pass.getText().toString();

                String sUser = "admin";
                String sPassword = "123";

                if (username.isEmpty() || password.isEmpty()) {

                    Toast t = Toast.makeText(getApplicationContext(), "Username dan password wajib diisi!", Toast.LENGTH_LONG); //lengthlong?
                    t.show();
                } else {
                    if (username.equals(sUser) && password.equals(sPassword)) {
                        Toast t = Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_LONG); //lengthlong?
                        t.show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast b = Toast.makeText(getApplicationContext(), "Username atau Password salah!", Toast.LENGTH_LONG);
                        b.show();
                    }

                }
            }
        });
    }
}