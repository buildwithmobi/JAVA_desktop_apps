package com.FlashCard.com;

public class FlashCard {
    private String question;


    public FlashCard(String q, String a){
        question = q;
        answer = a;
    }
    public String getAnswer() {
        return answer;
    }

    private String answer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }




}
