package com.example.ray.fragmentwithrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/*
此範例展示
1. fragment裡面使用recycle view達到list的效果
2. 參數從activity利用setArgument()傳給Fragment
3. 參數從fragment利用callback function傳回activity

activity顯示fragment, fragment使用自己的layout放進recycle view.
建立fragment時可設定想要的行數(colNum), 此參數即為activity傳給fragment知參數.
fragment內必須宣告interface給activity實作. 實作內容為, 按到item會秀出toast. 因此必須知道按到哪個資料內容, 此資料內容就是利用callback function從fragment傳回activity.
因此, 在activity實作callback function實現傳回的資料以toast顯示.

 */
public class MainActivity extends AppCompatActivity implements ItemListFragment.OnItemListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加入資料, 使用ArrayList
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("A1", "A2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("B1", "B2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("C1", "C2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("D1", "D2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("E1", "E2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("F1", "F2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("G1", "G2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("H1", "H2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("I1", "I2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("A1", "A2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("B1", "B2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("C1", "C2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("D1", "D2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("E1", "E2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("F1", "F2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("G1", "G2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("H1", "H2"));
        ItemContent.itemDataArrayList.add(new ItemContent.ItemData("I1", "I2"));

        //顯示fragment, 利用寫定的static function newInstance(colNum)可指定欄位數目
        getFragmentManager().beginTransaction().add(R.id.fl_main, ItemListFragment.newInstance(4)).commit();
    }

    //實作callback function, 當使用者按下recycle view其中一個view, 回傳該view所包含的資料(ItemData)
    @Override
    public void onClickItem(ItemContent.ItemData itemData) {
        //這裡將所收到的資料用toast顯示
        Toast.makeText(this, "Text1=" + itemData.text1 + ", Text2=" + itemData.text2, Toast.LENGTH_SHORT).show();
    }
}
