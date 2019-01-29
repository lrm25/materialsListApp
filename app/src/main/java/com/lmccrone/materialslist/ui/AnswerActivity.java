package com.lmccrone.materialslist.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lmccrone.materialslist.R;

public class AnswerActivity extends AppCompatActivity {

    TabLayout.Tab itemTab;
    TabLayout.Tab questionTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TabLayout tl = findViewById(R.id.tabLayout);
        itemTab = tl.newTab();
        itemTab.setText("Items");
        questionTab = tl.newTab();
        questionTab.setText("Questions");
        tl.addTab(itemTab);
        tl.addTab(questionTab);
    }
}
