package com.lmccrone.materialslist.bus;

public class Item {

    private String name;

    public Item(String name) {
        if ((null == name) || (name.isEmpty())) {
            // TODO throw exception
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (null == object) {
            return false;

        } else if (!(object instanceof Item)) {
            return false;

        } else if (!name.equalsIgnoreCase(((Item)object).name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
