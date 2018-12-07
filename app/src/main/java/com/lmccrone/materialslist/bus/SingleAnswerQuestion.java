package com.lmccrone.materialslist.bus;

import java.util.ArrayList;

import com.lmccrone.materialslist.bus.MultipleChoiceQuestion;

public class SingleAnswerQuestion extends MultipleChoiceQuestion {

    public String text;

    public SingleAnswerQuestion(Answer parentAnswer, String text, ArrayList<Answer> childAnswers) {

        super(parentAnswer, text, childAnswers);
    }

    public void selectAnswer(int answerId) {

    }
}
