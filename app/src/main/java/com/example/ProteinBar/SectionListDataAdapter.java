package com.example.proteinbar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);

        Glide.with(mContext).load(singleItem.getPhoto()).into(holder.itemImage);

        holder.price.setText(String.valueOf(singleItem.getPrice()));
        holder.tvtitle.setText(singleItem.getName());

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvtitle;
        TextView price;
        ImageView itemImage;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvtitle = view.findViewById(R.id.tvtitle);
            this.itemImage = view.findViewById(R.id.itemImage);
            this.price = view.findViewById(R.id.tvprice);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            SingleItemModel current = itemsList.get(getAdapterPosition());
            final String itemName = current.getName();
            final int itemPrice = current.getPrice();
            final int itemid = current.getId();
            final String itemImage = current.getPhoto();
            Intent intent = new Intent(mContext, CustomSingleListActivity.class);
                intent.putExtra("name", itemName);
                intent.putExtra("price", itemPrice);
                intent.putExtra("id", itemid);
                intent.putExtra("image",String.valueOf(itemImage));
                mContext.startActivity(intent);

        }
    }
}
