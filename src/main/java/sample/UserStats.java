package sample;

public class UserStats {
    private int surveyNumber;
    private int[] surveyAverage;
    private int[] surveyAnswNumber;


    public UserStats(int surveyNumber, int[] surveyAverage, int[] surveyAnswNumber) {
        this.surveyNumber = surveyNumber;
        this.surveyAverage = surveyAverage;
        this.surveyAnswNumber = surveyAnswNumber;
    }

    public int getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(int surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public int[] getSurveyAverage() {
        return surveyAverage;
    }

    public void setSurveyAverage(int[] surveyAverage) {
        this.surveyAverage = surveyAverage;
    }

    public int[] getSurveyAnswNumber() {
        return surveyAnswNumber;
    }

    public void setSurveyAnswNumber(int[] surveyAnswNumber) {
        this.surveyAnswNumber = surveyAnswNumber;
    }

    @Override
    public String toString() {
        return "UserStats{" +
            "surveyAnswNumber=" +surveyAnswNumber+
                    ", surveyAverage="+surveyAverage+
                "surveyAnswNumber="+surveyAnswNumber+"}";
    }
}
