package com.lmccrone.materialslist.bus;

import java.util.ArrayList;
import java.util.TreeSet;

public class ItemList {

    private ArrayList<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<Item>();
    }

    public ArrayList<Item> getList() {
        return itemList;
    }

    // TODO add reason
    public boolean isAddable(String item) {
        if ((null == item) || item.isEmpty()) {
            return false;
        }
        return true;
    }

    public void add(Item item) throws ItemException {

        String itemName;
        int compareVal;
        int listSize = itemList.size();

        if (null == item) {
            // TODO throw exception
        }
        itemName = item.getName();
        if ((null == itemName) || (itemName.isEmpty())) {
            // TODO throw exception
        }
        // place in alphabetical order
        int itemPos = 0;
        for (; itemPos < listSize; itemPos++) {

            Item existingItem = itemList.get(itemPos);
            compareVal = existingItem.getName().compareToIgnoreCase(itemName);
            if (compareVal < 0) {
                continue;

            } else if (0 == compareVal) {
                throw new ItemException(String.format("Item '%s' already exists", itemName));

            } else {
                break;
            }
        }
        itemList.add(itemPos, item);
    }

    public void delete(Item item) {
        itemList.remove(item);
    }

    public boolean contains(String item) {
        return itemList.contains(item);
    }
}
