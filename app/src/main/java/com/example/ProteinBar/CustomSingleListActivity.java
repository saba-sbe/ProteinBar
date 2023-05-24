package com.example.proteinbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomSingleListActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button basket;
    ImageView photo , increase , decrease;
    TextView name , count , price , textprice;
    RadioButton nimkilo , yekkilo;
    String nameofproduct;
    int priceofproduct ,idofproduct;
    String imageofproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_single_list);

        findViews();
        setToolbar();
        final Intent intent = getIntent();
        nameofproduct = intent.getStringExtra("name");
        priceofproduct = intent.getIntExtra("price",0);
        idofproduct = intent.getIntExtra("id",0);
        imageofproduct = intent.getStringExtra("image");

        if (intent != null){
            name.setText(nameofproduct);
            price.setText(String.valueOf(priceofproduct));
            Glide.with(CustomSingleListActivity.this).load(imageofproduct).into(photo);
        }

        if (count.getText().toString().equals("1")){
            decrease.setVisibility(View.GONE);
        }

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(count.getText().toString());
                int numprice = Integer.parseInt(price.getText().toString());
                count.setText(String.valueOf(num + 1));
                price.setText(String.valueOf(numprice + numprice));
                decrease.setVisibility(View.VISIBLE);
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(count.getText().toString());
                int numprice = Integer.parseInt(price.getText().toString());
                count.setText(String.valueOf(num - 1));
                price.setText(String.valueOf(numprice/2));
                if (count.getText().toString().equals("1")){
                    decrease.setVisibility(View.GONE);
                }
            }
        });


        nimkilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textprice.setText("قیمت (نیم کیلو):");
                price.setText(String.valueOf(priceofproduct));
            }
        });
        yekkilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textprice.setText("قیمت (یک کیلو):");
                price.setText(String.valueOf(priceofproduct*2));
            }
        });


        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CustomSingleListActivity.this , BasketActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("price",price.getText());
                intent.putExtra("id",idofproduct);
                intent.putExtra("image",imageofproduct);
                intent.putExtra("number",count.getText());
                Toast.makeText(CustomSingleListActivity.this, "این محصول به سبد خرید شما اضافه شد", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }

    private void findViews() {
        photo = findViewById(R.id.custom_single_list_image);
        increase = findViewById(R.id.custom_single_list_increase);
        decrease = findViewById(R.id.custom_single_list_decrease);
        name = findViewById(R.id.custom_single_list_name);
        nimkilo = findViewById(R.id.custom_single_list_half_kilo);
        yekkilo = findViewById(R.id.custom_single_list_one_kilo);
        count = findViewById(R.id.custom_single_list_count);
        price = findViewById(R.id.custom_single_list_price);
        toolbar = findViewById(R.id.custom_single_list_toolbar);
        basket = findViewById(R.id.custom_single_list_basket);
        textprice = findViewById(R.id.textView13);

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_basket_product:
                startActivity(new Intent(CustomSingleListActivity.this , BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
