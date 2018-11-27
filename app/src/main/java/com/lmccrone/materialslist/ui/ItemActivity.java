package com.lmccrone.materialslist.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lmccrone.materialslist.R;
import com.lmccrone.materialslist.app.ItemManager;
import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.ItemException;

public class ItemActivity extends AppCompatActivity {

    private ItemManager itemManager;
    ArrayAdapter<Item> arrayAdapter;

    public ItemActivity() {
        itemManager = ItemManager.instance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ListView listView = findViewById(R.id.itemListView);
        arrayAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1,
                itemManager.getItemList())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getName());
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> list, final View v,
                                    final int position, final long id) {
                v.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                //TextView textView = (TextView) arrayAdapter.getView(pos)
                //textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        final Button button = findViewById(R.id.addButton);
        final TextView textView = findViewById(R.id.addItemText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem(textView.getText().toString());
            }
        });
    }

    private void addItem(String itemName) {

        try {
            itemManager.addItem(itemName);

        } catch (ItemException ie) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(ie.getMessage());
            alertDialogBuilder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        ListView listView = findViewById(R.id.itemListView);
        arrayAdapter.notifyDataSetChanged();
    }
}
