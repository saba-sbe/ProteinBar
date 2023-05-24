package com.example.proteinbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText username, password;
    TextInputLayout editusername ,editpassword;
    Button signIn, signUp;
    Toolbar toolbar;
    private static String link = "http://192.168.1.9/goshtaloo/proteinbar/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findViews();
        setToolbar();
        clicks();
    }

    private boolean validateusername() {

        if (username.getText().toString().trim().isEmpty()){
            editusername.setError("نام کاربری خود را وارد کنید");
            requestFocus(username);
            return false;
        }else {
            editusername.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validatepassword() {
        if (password.getText().toString().trim().isEmpty()){
            editpassword.setError("رمز عبور خود را وارد کنید");
            requestFocus(password);
            return false;
        }else {
            editpassword.setErrorEnabled(false);
        }
        return true;
    }

    private void clicks() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String musername = username.getText().toString().trim();
                String mpass = password.getText().toString().trim();
                if (!validateusername() || !validatepassword()){
                    return;
                }else{
                    Login(musername,mpass);
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    private void Login(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.contains("1")){
                        Toast.makeText(SignInActivity.this, "وارد شدید", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, OriginalPageActivity.class));
                    }else {
                        Toast.makeText(SignInActivity.this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this, "error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews() {
        editusername = findViewById(R.id.sign_in_username);
        editpassword = findViewById(R.id.sign_in_password);
        username = findViewById(R.id.sign_in_username_edit);
        password = findViewById(R.id.sign_in_password_edit);
        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.sign_in_sign_up);
        toolbar = findViewById(R.id.toolbar_sign_in);
    }


}
