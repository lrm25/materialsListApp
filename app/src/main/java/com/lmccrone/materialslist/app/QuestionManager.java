package com.lmccrone.materialslist.app;

import com.lmccrone.materialslist.bus.AllItemList;
import com.lmccrone.materialslist.bus.Answer;
import com.lmccrone.materialslist.bus.Item;
import com.lmccrone.materialslist.bus.MultipleChoiceQuestion;
import com.lmccrone.materialslist.bus.Question;
import com.lmccrone.materialslist.bus.QuestionException;
import com.lmccrone.materialslist.bus.QuestionList;

import java.util.ArrayList;

/**
 * Class which handles the addition, retrieval, and removal of questions
 */
public class QuestionManager {

    /** Data class where questions are actually stored */
    private QuestionList questionList;

    /** Singleton object */
    private static QuestionManager instance = null;

    /**
     * Get singleton
     */
    public static QuestionManager instance() {
        if (null == instance) {
            instance = new QuestionManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private QuestionManager() {

        questionList = new QuestionList();
    }

    /**
     * Add a main question (no parent answers)
     * @param text the answer's text
     * @throws QuestionException malformed answer, or already exists
     */
    public void addMainQuestion(String text) throws QuestionException {

        addQuestion(null, text);
    }

    /**
     * Add a question to an answer
     *
     * @param parentAnswer the answer to add a question to
     * @param text the answer's text
     * @throws QuestionException malformed question, or question already
     *  exists (TODO)
     */
    public void addQuestion(Answer parentAnswer, String text) throws QuestionException {

        questionList.add(new MultipleChoiceQuestion(null, text, null));
    }

    /**
     * Return a question
     * 
     * @param parentAnswer the answer that leads to this question being asked
     * @param text the answer's text
     */
    public Question get(Answer parentAnswer, String text) {

        return questionList.get(parentAnswer, text);
    }

    /**
     * TODO add parent answer
     * Return a list of all questions
     */
    public ArrayList<Question> getQuestionList() {
        return questionList.getList();
    }

    /**
     * TODO throw exception
     * Delete a question, if it exists
     */
    public void deleteQuestion(Question question) {

        questionList.delete(question);
    }
}
