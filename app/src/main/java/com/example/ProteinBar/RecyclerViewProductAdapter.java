package com.example.proteinbar;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public  class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.productviewholder> {

    private Context mCtx;
    private ArrayList<SingleItemModel> itemsList;

    public RecyclerViewProductAdapter(Context context, ArrayList<SingleItemModel> itemsList){
        this.itemsList = itemsList;
        this.mCtx = context;
    }

    @Override
    public productviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_list_card_view, null ,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        productviewholder mh = new productviewholder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(productviewholder holder, int position) {
        SingleItemModel singleItem = itemsList.get(position);

        Glide.with(mCtx).load(singleItem.getPhoto()).into(holder.itemImage);

        holder.price.setText(String.valueOf(singleItem.getPrice()));
        holder.title.setText(singleItem.getName());
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class productviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView price;
        ImageView itemImage;

        public productviewholder(View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.more_list_card_view_name);
            this.itemImage = itemView.findViewById(R.id.more_list_card_view_image);
            this.price = itemView.findViewById(R.id.more_list_card_view_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            SingleItemModel current = itemsList.get(getAdapterPosition());
            final String itemName = current.getName();
            final int itemPrice = current.getPrice();
            final int itemid = current.getId();
            final String itemImage = current.getPhoto();
            Intent intent = new Intent(mCtx, CustomSingleListActivity.class);
            intent.putExtra("name", itemName);
            intent.putExtra("id", itemid);
            intent.putExtra("price", itemPrice);
            intent.putExtra("image", String.valueOf(itemImage));
            mCtx.startActivity(intent);
        }
    }
}
