package com.lmccrone.materialslist.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lmccrone.materialslist.R;
import com.lmccrone.materialslist.app.ItemManager;
import com.lmccrone.materialslist.app.QuestionManager;
import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.ItemException;
import com.lmccrone.materialslist.bus.Question;
import com.lmccrone.materialslist.bus.QuestionException;

public class QuestionActivity extends AppCompatActivity {



    private QuestionManager questionManager;
    private QuestionActivity.QuestionSelectHandler questionSelectHandler;
    ArrayAdapter<Question> arrayAdapter;
    private ListView listView;
    //private Item selectedItem;
    private View currentSelectionView;
    private int currentSelectionPos;

    private QuestionActivity.DeleteVerifier deleteVerifier = null;

    public QuestionActivity() {

        questionManager = QuestionManager.instance();
        questionSelectHandler = new QuestionActivity.QuestionSelectHandler();
    }

    private class QuestionSelectHandler {

        private Question selectedQuestion = null;

        public QuestionSelectHandler() {
        }

        public Question getSelectedQuestion() {
            return selectedQuestion;
        }

        public void setSelectedQuestion(Question question) {
            selectedQuestion = question;
            boolean questionSelected = (null == question) ? false : true;
            updateItemEditButtons(questionSelected);
        }
    }

    public void updateItemEditButtons(boolean itemSelected) {
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setEnabled(itemSelected);
        Button editButton = findViewById(R.id.editButton);
        editButton.setEnabled(itemSelected);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        listView = findViewById(R.id.questionListView);
        arrayAdapter = new ArrayAdapter<Question>(this,
                android.R.layout.simple_list_item_1,
                questionManager.getQuestionList())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getText());
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> list, final View v,
                                    final int position, final long id) {

                setSelectedQuestion(v, position);
                //TextView textView = (TextView) arrayAdapter.getView(pos)
                //textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        final Button button = findViewById(R.id.addButton);
        final TextView textView = findViewById(R.id.addQuestionText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addQuestion(textView.getText().toString());
            }
        });

        final Button editButton = findViewById(R.id.editButton);
        //final TextView textView = findViewById(R.id.addQuestionText);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getOuterClass(), EditQuestionActivity.class);
                intent.putExtra("question", questionSelectHandler.getSelectedQuestion().getText());
                startActivity(intent);
            }
        });

        final Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Question question = questionSelectHandler.getSelectedQuestion();
                if (null != question) {
                    if (null == deleteVerifier) {
                        deleteVerifier = new QuestionActivity.DeleteVerifier(getOuterClass());
                    }
                    deleteVerifier.setQuestion(question);
                    deleteVerifier.verifyDelete();
                }
            }
        });
        updateItemEditButtons(false);
    }

    private QuestionActivity getOuterClass() {
        return this;
    }

    private void setSelectedQuestion(final int position) {

        setSelectedQuestion(listView.getChildAt(position), position);
    }

    private void setSelectedQuestion(final View v, final int position) {

        if (null != currentSelectionView) {
            currentSelectionView.setSelected(false);
            currentSelectionView.setBackgroundColor(getResources().getColor(R.color.white));
        }
        questionSelectHandler.setSelectedQuestion(arrayAdapter.getItem(position));
        currentSelectionView = v;
        currentSelectionView.setSelected(true);
        currentSelectionView.setBackgroundColor(getResources().getColor(
                R.color.lightBlue));
        currentSelectionPos = position;
    }

    private void deleteQuestion(Question question) {

        questionManager.deleteQuestion(question);
        arrayAdapter.notifyDataSetChanged();
        if (!arrayAdapter.isEmpty()) {
            setSelectedQuestion(0 < currentSelectionPos ? --currentSelectionPos : 0);

        } else {
            questionSelectHandler.setSelectedQuestion(null);
            currentSelectionView = null;
        }
    }

    private void addQuestion(String questionName) {

        try {
            questionManager.addQuestion(null, questionName);

        } catch (QuestionException ie) {
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

        private QuestionActivity outer;
        private Question question;

        public DeleteVerifier(QuestionActivity outer) {
            this.outer = outer;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public void verifyDelete() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(outer);
            alertDialogBuilder.setMessage(String.format("Delete %s.  Are you sure?", question.getText()));
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            outer.deleteQuestion(question);
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
