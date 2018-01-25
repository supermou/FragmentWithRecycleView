package com.example.ray.fragmentwithrecycleview;

import java.util.ArrayList;

/**
 * Created by Ray on 2018/1/25.
 */

public class ItemContent {

    static ArrayList<ItemData> itemDataArrayList = new ArrayList<>();

    static class ItemData {
        String text1;
        String text2;

        public ItemData(String text1, String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }
    }
}
