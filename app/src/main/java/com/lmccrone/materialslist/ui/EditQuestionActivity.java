package com.lmccrone.materialslist.ui;

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
import com.lmccrone.materialslist.app.QuestionManager;
import com.lmccrone.materialslist.bus.Answer;
import com.lmccrone.materialslist.bus.MultipleChoiceQuestion;
import com.lmccrone.materialslist.bus.Question;

public class EditQuestionActivity extends AppCompatActivity {

    private MultipleChoiceQuestion mcq;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private View currentSelectionView;
    private int currentSelectionPos;

    private EditQuestionActivity getOuterClass() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        TextView tv = findViewById(R.id.questionText);
        String str = getIntent().getStringExtra("question");
        Question q = QuestionManager.instance().get(null, str);
        mcq = (MultipleChoiceQuestion)q;
        tv.setText(mcq.getText());

        final Button editAnswerButton = findViewById(R.id.editAnswerButton);
        editAnswerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getOuterClass(), AnswerActivity.class);
                startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.addAnswerButton);
        final TextView addAnswerText = findViewById(R.id.addAnswerText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mcq.addAnswer(new Answer(addAnswerText.getText().toString()));
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView = findViewById(R.id.answerList);
        arrayAdapter = new ArrayAdapter<Answer>(this,
                android.R.layout.simple_list_item_1,
                mcq.getAnswers())
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

                setSelectedAnswer(v, position);
            }
        });
    }

    private void setSelectedAnswer(final int position) {

        setSelectedAnswer(listView.getChildAt(position), position);
    }

    private void setSelectedAnswer(final View v, final int position) {

        if (null != currentSelectionView) {
            currentSelectionView.setSelected(false);
            currentSelectionView.setBackgroundColor(getResources().getColor(R.color.white));
        }
        // questionSelectHandler.setSelectedQuestion(arrayAdapter.getItem(position));
        currentSelectionView = v;
        currentSelectionView.setSelected(true);
        currentSelectionView.setBackgroundColor(getResources().getColor(
                R.color.lightBlue));
        currentSelectionPos = position;
    }
}
