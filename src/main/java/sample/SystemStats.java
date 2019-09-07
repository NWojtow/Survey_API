package sample;

import sample.entity.User;

import java.util.HashMap;

public class SystemStats {
//   private HashMap<String, User> surveyNumberRanking;
//   private HashMap<String, User> answeNumberRanking;
   private float averageSurveyToAnswer;
   private float averageSurveyToUser;
   private int surveyNumber;
   private int userNumber;
   private int answerNumber;

    public SystemStats(float averageSurveyToAnswer, float averageSurveyToUser, int surveyNumber, int userNumber, int answerNumber) {
        this.averageSurveyToAnswer = averageSurveyToAnswer;
        this.averageSurveyToUser = averageSurveyToUser;
        this.surveyNumber = surveyNumber;
        this.userNumber = userNumber;
        this.answerNumber = answerNumber;
    }
}
