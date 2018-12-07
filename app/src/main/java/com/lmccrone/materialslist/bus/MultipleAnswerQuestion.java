package com.lmccrone.materialslist.bus;

import java.util.ArrayList;

public class MultipleAnswerQuestion extends MultipleChoiceQuestion {

    public ArrayList<Answer> currentAnswers;

    public MultipleAnswerQuestion(Answer parentAnswer, String text, ArrayList<Answer> childAnswers) {
        super(parentAnswer, text, childAnswers);
    }

    public void addAnswer(Answer answer) {

    }

    public void submitAnswers() {

    }
}
