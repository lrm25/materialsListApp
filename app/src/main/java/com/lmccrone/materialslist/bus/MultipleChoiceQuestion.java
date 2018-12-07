package com.lmccrone.materialslist.bus;

import java.util.ArrayList;

import com.lmccrone.materialslist.bus.Question;

public class MultipleChoiceQuestion extends Question {

    protected ArrayList<Answer> answers;

    public MultipleChoiceQuestion(Answer parentAnswer, String text, ArrayList<Answer> childAnswers) {

        super(QuestionType.MULTIPLE_CHOICE, parentAnswer, text);
        answers = (null != childAnswers) ? childAnswers : new ArrayList<Answer>();
    }

    public void addAnswer(Answer answer) {

        answers.add(answer);
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
