package sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue

    private int answer_id;
    private int survey_id;
    private int user_id;
    private int rating;

    public Answer(){};

    public Answer(int answer_id,int survey_id,int user_id,int rating){
        this.answer_id=answer_id;
        this.survey_id=survey_id;
        this.user_id=user_id;
        this.rating=rating;
    }


    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id="+answer_id+
                ", survey_id="+survey_id+
                ", user_id="+user_id+
                ", rating="+rating+"}";
    }
}
