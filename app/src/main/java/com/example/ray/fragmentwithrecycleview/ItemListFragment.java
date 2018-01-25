package com.example.ray.fragmentwithrecycleview;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ray on 2018/1/25.
 */

//實作Fragment
public class ItemListFragment extends Fragment {

    private final static String COLUMN_NUMBER = "column_number";
    private int colNum;
    private OnItemListFragmentInteractionListener onItemListFragmentInteractionListener;

    //利用此static function新增一個fragment instance
    public static ItemListFragment newInstance(int colNum) {
        ItemListFragment itemListFragment = new ItemListFragment();
        //利用bundle將參數放入fragment內, 即是將來自於activity的colNum傳遞給fragment
        Bundle args = new Bundle();
        args.putInt(COLUMN_NUMBER, colNum);
        itemListFragment.setArguments(args);
        return itemListFragment;
    }

    //當fragment真正要顯示到頁面時, 在onCreate將設定的colNum參數取出
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //取出所設定的參數
            colNum = getArguments().getInt(COLUMN_NUMBER);
        }
    }

    //建構fragment的view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        //fragment的view裡面有recycle view, 必須幫recycle view設定layout manager與設定recycle view的adapter讓其顯示
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView)view;
            //若activity傳來的資料colNum為1以下, 就像是list view一樣顯示.
            if (colNum <= 1)
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            //否則顯示grid view
            else
                recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), colNum));
            //設定recycle view的adapter, 給recycle view資料與listener
            recyclerView.setAdapter(new ItemRecycleViewAdapter(ItemContent.itemDataArrayList, onItemListFragmentInteractionListener));
        }
        //回傳是要回傳fragment的view, 並不是recycle view的view
        return view;
    }

    //onAttach是在顯示fragment最初被執行的function, 利用activity是否以實作listener, 判斷並將listener設定好, 之後傳給recycle view.
    //recycle view會利用此listener, 當recycle view的一個view被按時, 則執行此listener的callback function把參數傳給activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemListFragmentInteractionListener) {
            onItemListFragmentInteractionListener = (OnItemListFragmentInteractionListener) context;
        }
    }

    //listener的interface, 給activity實作, 用fragment的onAttach()設定進來, 再給recycle view看什麼時候觸發callback function.
    public interface OnItemListFragmentInteractionListener {
        void onClickItem(ItemContent.ItemData itemData);
    }
}
