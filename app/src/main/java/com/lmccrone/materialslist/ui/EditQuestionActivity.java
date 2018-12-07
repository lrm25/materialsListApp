package com.lmccrone.materialslist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        TextView tv = findViewById(R.id.questionView);
        String str = getIntent().getStringExtra("question");
        Question q = QuestionManager.instance().get(null, str);
        mcq = (MultipleChoiceQuestion)q;
        tv.setText(mcq.getText());

        final Button button = findViewById(R.id.addAnswerButton);
        final TextView textView = findViewById(R.id.addAnswerText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mcq.addAnswer(new Answer(textView.getText().toString()));
                TextView answerText = findViewById(R.id.answerText);
                answerText.setText(textView.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        ListView listView = findViewById(R.id.answerList);
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
    }
}
