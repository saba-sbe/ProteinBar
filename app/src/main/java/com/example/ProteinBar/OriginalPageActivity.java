package com.example.proteinbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OriginalPageActivity extends AppCompatActivity {

    private static String morghlink = "http://192.168.1.9/goshtaloo/proteinbar/morgh.php";
    private static String mahilink =  "http://192.168.1.9/goshtaloo/proteinbar/mahi.php";
    private static String gosalelink =  "http://192.168.1.9/goshtaloo/proteinbar/gosale.php";
    private static String gosfandlink ="http://192.168.1.9/goshtaloo/proteinbar/gosfand.php";
    ArrayList<SectionDataModel> productList;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_page);
        RecyclerView recycler_view = findViewById(R.id.recycle_view);

        productList = new ArrayList<SectionDataModel>();

        createData();
        setUpToolbar();
        setUpNavigationView();


        recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, productList);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_basket:
                startActivity(new Intent(OriginalPageActivity.this , BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpNavigationView() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_menu_sign_in:
                        startActivity(new Intent(OriginalPageActivity.this, SignInActivity.class));
                        break;
                    case R.id.navigation_menu_main:
                        startActivity(new Intent(OriginalPageActivity.this , OriginalPageActivity.class));
                        break;
                    case R.id.navigation_menu_basket:
                        startActivity(new Intent(OriginalPageActivity.this , BasketActivity.class));
                        break;
                    case R.id.navigation_menu_list:
                        startActivity(new Intent(OriginalPageActivity.this , ListProductActivity.class));
                        break;
                }
                return true;
            }
        });

    }

    private void setUpToolbar() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void createData() {
        for (int i = 1; i <= 4; i++) {
            final SectionDataModel dm = new SectionDataModel();
            final ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            if (i == 1){
                dm.setHeaderTitle("مرغ");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, morghlink, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject product = array.getJSONObject(i);
                                singleItem.add(new SingleItemModel(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getInt("type"),
                                        product.getInt("price"),
                                        product.getString("photo")
                                ));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OriginalPageActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OriginalPageActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                Volley.newRequestQueue(this).add(stringRequest);
            }else if (i == 2){
                dm.setHeaderTitle("ماهی");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, mahilink, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject product = array.getJSONObject(i);
                                singleItem.add(new SingleItemModel(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getInt("type"),
                                        product.getInt("price"),
                                        product.getString("photo")
                                ));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OriginalPageActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OriginalPageActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                Volley.newRequestQueue(this).add(stringRequest);
            }else if (i == 3){
                dm.setHeaderTitle("گوساله");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, gosalelink, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject product = array.getJSONObject(i);
                                singleItem.add(new SingleItemModel(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getInt("type"),
                                        product.getInt("price"),
                                        product.getString("photo")
                                ));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OriginalPageActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OriginalPageActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                Volley.newRequestQueue(this).add(stringRequest);
            }else if (i == 4){
                dm.setHeaderTitle("گوسفند");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, gosfandlink, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject product = array.getJSONObject(i);
                                singleItem.add(new SingleItemModel(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getInt("type"),
                                        product.getInt("price"),
                                        product.getString("photo")
                                ));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OriginalPageActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OriginalPageActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                Volley.newRequestQueue(this).add(stringRequest);
            }

            dm.setAllItemsInSection(singleItem);
            productList.add(dm);
        }
    }

}
