
package com.example.mid_exam_farah_shurrab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;

    boolean doubleBackToExitPressedOnce = false;

    Handler handler = new Handler(Looper.getMainLooper());

    Runnable resetBackFlag = () ->
            doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String email = etEmail.getText().toString().trim();

                String password = etPassword.getText().toString();

                boolean validEmail =
                        Patterns.EMAIL_ADDRESS.matcher(email).matches();

                boolean validPassword = password.length() >= 6;

                btnLogin.setEnabled(validEmail && validPassword);
            }
        };

        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ModeActivity.class
            );

            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            handler.removeCallbacks(resetBackFlag);

            finishAffinity();

            return;
        }

        doubleBackToExitPressedOnce = true;

        Snackbar.make(
                findViewById(R.id.main),
                "Press back again to exit",
                Snackbar.LENGTH_SHORT
        ).show();

        handler.postDelayed(resetBackFlag, 2000);
    }
}
