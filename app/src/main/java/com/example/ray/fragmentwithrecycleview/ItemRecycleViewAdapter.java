package com.example.ray.fragmentwithrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ray on 2018/1/25.
 */

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<ItemRecycleViewAdapter.ViewHolder> {

    //recycle view adapter要顯示資料與決定何實觸發listener, 因此傳進資料與listener
    private ArrayList<ItemContent.ItemData> itemDataArrayList;
    private ItemListFragment.OnItemListFragmentInteractionListener onItemListFragmentInteractionListener;

    //利用建構子設定
    public ItemRecycleViewAdapter(ArrayList<ItemContent.ItemData> itemDataArrayList, ItemListFragment.OnItemListFragmentInteractionListener onItemListFragmentInteractionListener) {
        this.itemDataArrayList = itemDataArrayList;
        this.onItemListFragmentInteractionListener = onItemListFragmentInteractionListener;
    }

    //建立ViewHolder, 將recycle view建立view, 回傳ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text1.setText(itemDataArrayList.get(position).text1);
        holder.text2.setText(itemDataArrayList.get(position).text2);

        //綁定資料的同時也可綁定listener, 當按下ViewHolder的view實, 呼叫callback function, 並將資料丟回activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListFragmentInteractionListener.onClickItem(itemDataArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDataArrayList.size();
    }

    //RecycleView的ViewHolder必須繼承自RecycleView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);            //不可省略
            this.itemView = itemView;   //記錄設定listener用

            //綁定View
            text1 = itemView.findViewById(R.id.txtView_text1);
            text2 = itemView.findViewById(R.id.txtView_text2);
        }
    }
}
