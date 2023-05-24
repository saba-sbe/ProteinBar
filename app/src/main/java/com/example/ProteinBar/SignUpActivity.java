package com.example.proteinbar;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;



public class SignUpActivity extends AppCompatActivity {
    TextInputEditText name, lastname, phone, password, address ,username;
    TextInputLayout editname , editlastname, editphone, editpassword, editaddress , editusername;
    Button signUp;
    Toolbar toolbar;
    private static String link = "http://192.168.1.9/goshtaloo/proteinbar/register.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViews();
        setToolbar();

        name.addTextChangedListener(new ValidationTextWatcher(editname));
        lastname.addTextChangedListener(new ValidationTextWatcher(editlastname));
        username.addTextChangedListener(new ValidationTextWatcher(editusername));
        password.addTextChangedListener(new ValidationTextWatcher(editpassword));
        phone.addTextChangedListener(new ValidationTextWatcher(editphone));
        address.addTextChangedListener(new ValidationTextWatcher(editaddress));

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatefirstname()|!validatelastname()|!validateusername()|!validatepassword()|!validatephone()|!validateaddress()){
                    return;
                }else{
                    Regist();
                }

            }
        });

    }

    private class ValidationTextWatcher implements TextWatcher{

        private View view;
        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            switch (view.getId()) {
                case R.id.sign_up_firstname_edit:
                    validatefirstname();
                    break;
                case R.id.sign_up_lastname_edit:
                    validatelastname();
                    break;
                case R.id.sign_up_username_edit:
                    validateusername();
                    break;
                case R.id.sign_up_password_edit:
                    validatepassword();
                    break;
                case R.id.sign_up_phone_edit:
                    validatephone();
                    break;
                case R.id.sign_up_address_edit:
                    validateaddress();
                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validatefirstname() {
        if (name.getText().toString().trim().isEmpty()){
            editname.setError("نام خود را وارد کنید");
            requestFocus(name);
            return false;
        }else{
            editname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatelastname() {
        if (lastname.getText().toString().trim().isEmpty()){
            editlastname.setError("نام خانوادگی خود را وارد کنید");
            requestFocus(lastname);
            return false;
        }else {
            editlastname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateusername() {
        if (username.getText().toString().trim().isEmpty()){
            editusername.setError("نام کاربری خود را وارد کنید");
            requestFocus(username);
            return false;
        }else if (username.getText().toString().length() > 15){
            editusername.setError("نام کاربری بزرگ است");
            return false;
        }else {
            editusername.setErrorEnabled(false);
        }
        return true;
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

    private boolean validatephone() {
        if (phone.getText().toString().trim().isEmpty()){
            editphone.setError("شماره همراه خود را وارد کنید");
            requestFocus(phone);
            return false;
        }else {
            editphone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateaddress() {
        if (address.getText().toString().trim().isEmpty()){
            editaddress.setError("آدرس خود را وارد کنید");
            requestFocus(address);
            return false;
        }else {
            editaddress.setErrorEnabled(false);
        }
        return true;
    }

    private void Regist() {
        final String name = this.name.getText().toString().trim();
        final String lastname = this.lastname.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String phone = this.phone.getText().toString().trim();
        final String address = this.address.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(SignUpActivity.this, "با موفقیت ثبت نام شدید", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this, "error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, "error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("lastname",lastname);
                params.put("username",username);
                params.put("password",password);
                params.put("phone",phone);
                params.put("address",address);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void findViews() {
        toolbar = findViewById(R.id.sign_up_toolbar);
        editname = findViewById(R.id.sign_up_firstname);
        editlastname = findViewById(R.id.sign_up_lastname);
        editpassword = findViewById(R.id.sign_up_password);
        editphone = findViewById(R.id.sign_up_phone);
        editaddress = findViewById(R.id.sign_up_address);
        editusername = findViewById(R.id.sign_up_username);
        signUp = findViewById(R.id.sign_up);
        name = findViewById(R.id.sign_up_firstname_edit);
        lastname = findViewById(R.id.sign_up_lastname_edit);
        username = findViewById(R.id.sign_up_username_edit);
        password = findViewById(R.id.sign_up_password_edit);
        phone = findViewById(R.id.sign_up_phone_edit);
        address = findViewById(R.id.sign_up_address_edit);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (toolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

}
