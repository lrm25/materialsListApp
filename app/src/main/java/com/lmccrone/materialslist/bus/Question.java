package com.lmccrone.materialslist.bus;

public class Question {

    public enum QuestionType {
        MULTIPLE_CHOICE,
        MULTIPLE_ANSWER
    }

    protected Answer parentAnswer;
    protected String text;

    protected enum AnswerStatus {
        IGNORED,
        UNANSWERED,
        PARTIALLY_ANSWERED,
        ANSWERED
    }

    public Question (QuestionType questionType, Answer parentAnswer, String text) {

        this.parentAnswer = parentAnswer;
        this.text = text;
    }

    @Override
    public boolean equals(Object object) {

        String qText;

        if (null == object) {
            return false;

        } else if (!(object instanceof Question)) {
            return false;

        } else if (null == (qText = ((Question)object).text)) {
            return false;

        } else if (!text.equalsIgnoreCase(qText)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return text.toLowerCase().hashCode();
    }

    public String getText() {
        return text;
    }
}
