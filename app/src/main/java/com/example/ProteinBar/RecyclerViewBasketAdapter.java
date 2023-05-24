package com.example.proteinbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewBasketAdapter extends RecyclerView.Adapter<RecyclerViewBasketAdapter.basketviewholder> {

    private Context mCtx;
    private ArrayList<BasketModel> basketList;


    public RecyclerViewBasketAdapter(Context context, ArrayList<BasketModel> basketList){
        this.basketList = basketList;
        this.mCtx = context;
    }

    @NonNull
    @Override
    public basketviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_cardview, null ,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        basketviewholder mh = new basketviewholder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull basketviewholder holder, int position) {
        BasketModel basketModel = basketList.get(position);
        Glide.with(mCtx).load(basketModel.getPhoto()).into(holder.itemImage);
        holder.price.setText(String.valueOf(basketModel.getPrice()));
        holder.title.setText(basketModel.getName());
        holder.number.setText(String.valueOf(basketModel.getNumber()));

    }

    @Override
    public int getItemCount() {
        return (null != basketList ? basketList.size() : 0);
    }

    public class basketviewholder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;
        TextView number;
        ImageView itemImage;

        public basketviewholder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.basket_list_card_view_name);
            this.itemImage = itemView.findViewById(R.id.basket_list_card_view_image);
            this.price = itemView.findViewById(R.id.basket_card_view_final_price);
            this.number = itemView.findViewById(R.id.basket_card_view_count);

        }
    }
}
