package com.example.proteinbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MoreListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    String typeofproduct;
    private static String morghlink = "http://192.168.1.9/goshtaloo/proteinbar/morgh.php";
    private static String mahilink =  "http://192.168.1.9/goshtaloo/proteinbar/mahi.php";
    private static String gosalelink =  "http://192.168.1.9/goshtaloo/proteinbar/gosale.php";
    private static String gosfandlink ="http://192.168.1.9/goshtaloo/proteinbar/gosfand.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);
        findviews();
        setToolbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(MoreListActivity.this));
        recyclerView.setHasFixedSize(true);
        final ArrayList<SingleItemModel> itemsList = new ArrayList<>();
        typeofproduct = getIntent().getStringExtra("type");

        if (typeofproduct.equals("morgh")){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, morghlink, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++){
                            JSONObject products = array.getJSONObject(i);
                            itemsList.add(new SingleItemModel(
                                    products.getInt("id"),
                                    products.getString("name"),
                                    products.getInt("type"),
                                    products.getInt("price"),
                                    products.getString("photo")
                            ));
                            RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(MoreListActivity.this , itemsList);
                            recyclerView.setAdapter(adapter);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MoreListActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MoreListActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            Volley.newRequestQueue(this).add(stringRequest);

        }else if(typeofproduct.equals("mahi")){

            StringRequest stringRequest = new StringRequest(Request.Method.GET, mahilink, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++){
                            JSONObject product = array.getJSONObject(i);
                            itemsList.add(new SingleItemModel(
                                    product.getInt("id"),
                                    product.getString("name"),
                                    product.getInt("type"),
                                    product.getInt("price"),
                                    product.getString("photo")
                            ));
                            RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(MoreListActivity.this , itemsList);
                            recyclerView.setAdapter(adapter);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MoreListActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MoreListActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            Volley.newRequestQueue(this).add(stringRequest);

        }else if(typeofproduct.equals("gosale")){

            StringRequest stringRequest = new StringRequest(Request.Method.GET, gosalelink, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++){
                            JSONObject product = array.getJSONObject(i);
                            itemsList.add(new SingleItemModel(
                                    product.getInt("id"),
                                    product.getString("name"),
                                    product.getInt("type"),
                                    product.getInt("price"),
                                    product.getString("photo")
                            ));
                            RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(MoreListActivity.this , itemsList);
                            recyclerView.setAdapter(adapter);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MoreListActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MoreListActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            Volley.newRequestQueue(this).add(stringRequest);

        }else if(typeofproduct.equals("gosfand")){

            StringRequest stringRequest = new StringRequest(Request.Method.GET, gosfandlink, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++){
                            JSONObject product = array.getJSONObject(i);
                            itemsList.add(new SingleItemModel(
                                    product.getInt("id"),
                                    product.getString("name"),
                                    product.getInt("type"),
                                    product.getInt("price"),
                                    product.getString("photo")
                            ));
                            RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(MoreListActivity.this , itemsList);
                            recyclerView.setAdapter(adapter);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MoreListActivity.this, "error"+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MoreListActivity.this, "error:"+ error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            Volley.newRequestQueue(this).add(stringRequest);

        }


    }


    private void findviews() {
        recyclerView = findViewById(R.id.more_list_recycler_view);
        toolbar = findViewById(R.id.more_list_toolbar);

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
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_basket_product:
                startActivity(new Intent(MoreListActivity.this, BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
