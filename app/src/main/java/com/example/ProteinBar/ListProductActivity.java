package com.example.proteinbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ListProductActivity extends AppCompatActivity {

    TextView chicken , fish , sheep , cow;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        findViews();
        clicks();
        setToolbar();

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
                startActivity(new Intent(ListProductActivity.this , BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clicks() {

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this , MoreListActivity.class);
                intent.putExtra("type" , "morgh");
                startActivity(intent);
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this , MoreListActivity.class);
                intent.putExtra("type" , "mahi");
                startActivity(intent);
            }
        });

        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this , MoreListActivity.class);
                intent.putExtra("type" , "gosale");
                startActivity(intent);
            }
        });

        sheep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this , MoreListActivity.class);
                intent.putExtra("type" , "gosfand");
                startActivity(intent);
            }
        });

    }

    private void findViews() {

        chicken = findViewById(R.id.list_product_chicken);
        fish = findViewById(R.id.list_product_fish);
        cow = findViewById(R.id.list_product_cow);
        sheep = findViewById(R.id.list_product_sheep);
        toolbar = findViewById(R.id.list_product_toolbar);
    }

}
