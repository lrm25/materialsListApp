package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.ItemException;
import com.lmccrone.materialslist.bus.ItemList;

import java.util.ArrayList;

public class ItemManager {

    private ItemList itemList;
    private static ItemManager itemManager;

    public static ItemManager instance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }

    private ItemManager() {
        itemList = new ItemList();
    }

    public void addItem(String item) throws ItemException {

        itemList.add(new Item(item));
    }

    public void deleteItem(String item) {

        itemList.delete(new Item(item));
    }

    public ArrayList<Item> getItemList() {
        return itemList.getList();
    }
}
