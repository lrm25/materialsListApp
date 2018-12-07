package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.ItemException;
import com.lmccrone.materialslist.bus.AllItemList;

import java.util.ArrayList;

public class ItemManager {

    private AllItemList itemList;
    private static ItemManager itemManager;

    public static ItemManager instance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }

    private ItemManager() {
        itemList = new AllItemList();
    }

    public void addItem(String item) throws ItemException {

        itemList.add(new Item(item));
    }

    public void deleteItem(Item item) {

        itemList.delete(item);
    }

    public ArrayList<Item> getItemList() {
        return itemList.getList();
    }
}
