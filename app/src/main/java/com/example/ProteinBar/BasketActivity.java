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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView price , textOfPrice , emptyBasket;
    RecyclerView recyclerView;
    Button kharid;

    BasketModel basketModel;
    String nameofproduct;
    int priceofproduct ,idofproduct ,number;
    String imageofproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        findViews();
        setUpToolbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(BasketActivity.this));
        recyclerView.setHasFixedSize(true);
        final Intent intent = getIntent();
        nameofproduct = intent.getStringExtra("name");
        priceofproduct = intent.getIntExtra("price",0);
        idofproduct = intent.getIntExtra("id",0);
        imageofproduct = intent.getStringExtra("image");
        number = intent.getIntExtra("number",0);
        basketModel = new BasketModel(idofproduct , nameofproduct , priceofproduct , number , imageofproduct );
        readRecords();



    }

    public void readRecords() {


        List<BasketModel> baskets = new BasketController(this).read();

        if (baskets.size() > 0) {

            for (BasketModel obj : baskets) {

                int id = obj.getId();
                String basketName = obj.getName();
                int basketPrice = obj.getPrice();
                int basketNumber = obj.getNumber();
                String basketImage = obj.getPhoto();

                RecyclerViewBasketAdapter adapter = new RecyclerViewBasketAdapter(BasketActivity.this , (ArrayList<BasketModel>) baskets);
                recyclerView.setAdapter(adapter);
                emptyBasket.setVisibility(View.GONE);

                if (id == basketModel.getId()){
//                    boolean updateSuccessful = BasketController.update(basketModel);
//
//                    if(updateSuccessful){
//                        Toast.makeText(BasketActivity.this, "Basket record was updated.", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(BasketActivity.this, "Unable to update Basket record.", Toast.LENGTH_SHORT).show();
//                    }
                }else {
                    boolean createSuccessful = new BasketController(BasketActivity.this).create(basketModel);
                    emptyBasket.setVisibility(View.GONE);
                    RecyclerViewBasketAdapter adapter1 = new RecyclerViewBasketAdapter(BasketActivity.this , (ArrayList<BasketModel>) baskets);
                    recyclerView.setAdapter(adapter1);


                    if(createSuccessful){
                        Toast.makeText(BasketActivity.this, "Basket information was saved.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BasketActivity.this, "Unable to save Basket information.", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }

        else {

            boolean createSuccessful = new BasketController(BasketActivity.this).create(basketModel);
            emptyBasket.setVisibility(View.GONE);
            RecyclerViewBasketAdapter adapter = new RecyclerViewBasketAdapter(BasketActivity.this , (ArrayList<BasketModel>) baskets);
            recyclerView.setAdapter(adapter);


            if(createSuccessful){
                Toast.makeText(BasketActivity.this, "Basket information was saved.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(BasketActivity.this, "Unable to save Basket information.", Toast.LENGTH_SHORT).show();
            }


        }

    }

    public void editRecord(final int basketId) {


    }

    public void countRecords() {
        int recordCount = new BasketController(this).count();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void findViews() {

        toolbar = findViewById(R.id.basket_toolbar);
        price = findViewById(R.id.basket_final_price_final);
        textOfPrice = findViewById(R.id.basket_final_price);
        emptyBasket = findViewById(R.id.basket_clear_text_view);
        recyclerView = findViewById(R.id.basket_recyclerView);
        kharid = findViewById(R.id.basket_continue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basket_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_basket:

                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
