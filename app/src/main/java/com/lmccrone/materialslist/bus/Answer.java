package com.lmccrone.materialslist.bus;

import java.util.ArrayList;
import java.util.TreeSet;

public class Answer {

    private int id = 0;

    private String text;

    private Question parentQuestion;

    private ArrayList<Question> childQuestions;

    private TreeSet<Item> items;

    public Answer(String text) {
        this.text = text;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getText() {
        return text;
    }

    public TreeSet<Item> getItems() {
        return items;
    }
}
