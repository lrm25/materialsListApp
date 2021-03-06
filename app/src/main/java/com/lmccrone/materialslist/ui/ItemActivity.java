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

import java.util.concurrent.atomic.AtomicBoolean;

public class ItemActivity extends AppCompatActivity {

    private ItemManager itemManager;
    private ItemSelectHandler itemSelectHandler;
    ArrayAdapter<Item> arrayAdapter;
    private ListView listView;
    //private Item selectedItem;
    private View currentSelectionView;
    private int currentSelectionPos;

    private DeleteVerifier deleteVerifier = null;

    public ItemActivity() {

        itemManager = ItemManager.instance();
        itemSelectHandler = new ItemSelectHandler();
    }

    private class ItemSelectHandler {

        private Item selectedItem = null;

        public ItemSelectHandler() {
        }

        public Item getSelectedItem() {
            return selectedItem;
        }

        public void setSelectedItem(Item item) {
            selectedItem = item;
            boolean itemSelected = (null == item) ? false : true;
            updateItemEditButtons(itemSelected);
        }
    }

    public void updateItemEditButtons(boolean itemSelected) {
        Button addButton = findViewById(R.id.deleteButton);
        addButton.setEnabled(itemSelected);
        Button renameButton = findViewById(R.id.renameButton);
        renameButton.setEnabled(itemSelected);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        listView = findViewById(R.id.itemListView);
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

                setSelectedItem(v, position);
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

        final Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Item item = itemSelectHandler.getSelectedItem();
                if (null != item) {
                    if (null == deleteVerifier) {
                        deleteVerifier = new DeleteVerifier(getOuterClass());
                    }
                    deleteVerifier.setItem(item);
                    deleteVerifier.verifyDelete();
                }
            }
        });
    }

    private ItemActivity getOuterClass() {
        return this;
    }

    private void setSelectedItem(final int position) {

        setSelectedItem(listView.getChildAt(position), position);
    }

    private void setSelectedItem(final View v, final int position) {

        if (null != currentSelectionView) {
            currentSelectionView.setSelected(false);
            currentSelectionView.setBackgroundColor(getResources().getColor(R.color.white));
        }
        itemSelectHandler.setSelectedItem(arrayAdapter.getItem(position));
        currentSelectionView = v;
        currentSelectionView.setSelected(true);
        currentSelectionView.setBackgroundColor(getResources().getColor(
                R.color.lightBlue));
        currentSelectionPos = position;
    }

    private void deleteItem(Item item) {

        itemManager.deleteItem(item);
        arrayAdapter.notifyDataSetChanged();
        if (!arrayAdapter.isEmpty()) {
            setSelectedItem(0 < currentSelectionPos ? --currentSelectionPos : 0);

        } else {
            itemSelectHandler.setSelectedItem(null);
            currentSelectionView = null;
        }
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

    private class DeleteVerifier {

        private ItemActivity outer;
        private Item item;

        public DeleteVerifier(ItemActivity outer) {
            this.outer = outer;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public void verifyDelete() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(outer);
            alertDialogBuilder.setMessage(String.format("Delete %s.  Are you sure?", item.getName()));
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            outer.deleteItem(item);
                        }
                    });
            alertDialogBuilder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
