package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.FieldList;

public abstract class FieldManager<T> {

    protected FieldList<T> fieldList;

    public void add(T t) {
        fieldList.add(t);
    }

    public void remove(T t) {
        fieldList.remove(t);
    }

    public void edit(T t) {
        fieldList.edit(t);
    }

    public void delete(T t) {
        fieldList.delete(t);
    }
}
