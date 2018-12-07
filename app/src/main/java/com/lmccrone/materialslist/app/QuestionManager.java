package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.AllItemList;
import com.lmccrone.materialslist.bus.Answer;
import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.MultipleChoiceQuestion;
import com.lmccrone.materialslist.bus.Question;
import com.lmccrone.materialslist.bus.QuestionException;
import com.lmccrone.materialslist.bus.QuestionList;

import java.util.ArrayList;

public class QuestionManager {

    private QuestionList questionList;
    private static QuestionManager instance = null;

    public static QuestionManager instance() {
        if (null == instance) {
            instance = new QuestionManager();
        }
        return instance;
    }

    private QuestionManager() {

        questionList = new QuestionList();
    }

    public void addMainQuestion(String text) throws QuestionException {

        addQuestion(null, text);
    }

    public void addQuestion(Answer parentAnswer, String text) throws QuestionException {

        questionList.add(new MultipleChoiceQuestion(null, text, null));
    }

    public Question get(Answer parentAnswer, String text) {

        return questionList.get(parentAnswer, text);
    }

    public ArrayList<Question> getQuestionList() {
        return questionList.getList();
    }

    public void deleteQuestion(Question question) {

        questionList.delete(question);
    }
}
