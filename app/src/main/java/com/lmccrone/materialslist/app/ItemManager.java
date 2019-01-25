package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.ItemException;
import com.lmccrone.materialslist.bus.AllItemList;

import java.util.ArrayList;

public class ItemManager {

    private AllItemList itemList;
    private static ItemManager itemManager;

    /**
     * Return singleton 
     */
    public static ItemManager instance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }

    /**
     * Constructor
     */
    private ItemManager() {
        itemList = new AllItemList();
    }

    /**
     * Add an item to the app's stored item
     * throws ItemException:  item already exists, has empty name, etc.
     */
    public void addItem(String item) throws ItemException {

        itemList.add(new Item(item));
    }

    /**
     * Remove item from list
     * TODO:  add exception
     */
    public void deleteItem(Item item) {

        itemList.delete(item);
    }

    /**
     * Get the full list of itmes stored on the app
     */
    public ArrayList<Item> getItemList() {
        return itemList.getList();
    }
}
