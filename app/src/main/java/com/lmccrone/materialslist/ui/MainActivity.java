package com.lmccrone.materialslist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lmccrone.materialslist.R;
import com.lmccrone.materialslist.ui.ItemActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button itemEditButton = findViewById(R.id.itemEditButton);
        itemEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openItemScreen();
            }
        });

        final Button questionEditButton = findViewById(R.id.questionEditButton);
        questionEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openQuestionEditScreen();
            }
        });
    }

    private void openItemScreen() {
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }

    private void openQuestionEditScreen() {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
