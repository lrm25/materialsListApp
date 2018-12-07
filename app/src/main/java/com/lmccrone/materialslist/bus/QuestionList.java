package com.lmccrone.materialslist.bus;

import java.util.ArrayList;

public class QuestionList {

    private ArrayList<Question> questionList;

    public QuestionList() {
        questionList = new ArrayList<Question>();
    }

    public ArrayList<Question> getQuestion() {
        return questionList;
    }

    // TODO add reason
    public boolean isAddable(String question) {
        if ((null == question) || question.isEmpty()) {
            return false;
        }
        return true;
    }

    public void add(Question question) throws QuestionException {

        String questionText;
        int compareVal;
        int listSize = questionList.size();

        if (null == question) {
            throw new QuestionException("Cannot add null item");
        }
        questionText = question.getText();
        if ((null == questionText) || (questionText.isEmpty())) {
            throw new QuestionException("Cannot add item without name");
        }
        questionList.add(question);
    }

    public Question get(Answer parentAnswer, String text) {
        int questionIndex;
        return 0 <= (questionIndex = questionList.indexOf(new MultipleChoiceQuestion(parentAnswer, text, null))) ?
                questionList.get(questionIndex) : null;
    }

    public ArrayList<Question> getList() {
        return questionList;
    }

    public void delete(Question question) {
        questionList.remove(question);
    }

    public boolean contains(String item) {
        return questionList.contains(item);
    }
}
