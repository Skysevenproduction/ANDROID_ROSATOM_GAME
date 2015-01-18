package ru.skyseven.rosatom_game_android.data_source;

/**
 * Created by aleksey.m on 10.12.14.
 */
public class QuestionItem {

    private String  question;
    private String  comment;
    private String  id;
    private String     answer;

    public QuestionItem(String id, String question, String comment,  String answer) {
        this.question = question;
        this.comment = comment;
        this.id = id;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
