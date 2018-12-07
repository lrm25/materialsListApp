package com.lmccrone.materialslist.bus;

public abstract class FieldList<T> {

    public abstract void add(T t);

    public abstract void remove(T t);

    public abstract void edit(T t);

    public abstract void delete(T t);
}
