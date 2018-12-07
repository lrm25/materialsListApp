package com.lmccrone.materialslist.bus;

import java.util.TreeSet;

public class SelectedItemList {

    private TreeSet<Item> selectedItems;

    public void addItem(Item item) {
        selectedItems.add(item);
    }

    public void addItems(TreeSet<Item> items) {
        selectedItems.addAll(items);
    }
}
