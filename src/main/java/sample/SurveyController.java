package sample;

import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.ResponseEntity;
import sample.DAO.AnswerDAO;
import sample.DAO.SurveyDAO;
import sample.DAO.UserDAO;
import sample.entity.Answer;
import sample.entity.Survey;
import sample.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyController {

    static public String newUser(String name) {
        Gson gson = new Gson();
        UserDAO udao = new UserDAO();
        User temp = new User();
        temp.setUsername(name);
        udao.add(temp);

        return gson.toJson(temp);
    }


    static public String newSurvey(int id, String title, String question) {
        UserDAO udao = new UserDAO();
        SurveyDAO surdao = new SurveyDAO();
        Gson gson = new Gson();

        User temp = udao.read(id);

        Survey buff = new Survey();
        buff.setUser_id(temp.getUser_id());
        buff.setQuestion(question);
        buff.setSurvey_title(title);

        surdao.add(buff);

        return gson.toJson(buff);
    }

    static public String userSurveys(int id) {
        SurveyDAO surdao = new SurveyDAO();
        String buff = "";


        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Gson gson = new Gson();
            Criteria criteria = session.createCriteria(Survey.class);
            List<Survey> temp = criteria.add(Restrictions.eq("user_id", id)).list();

//            for(int i=0;i<temp.size();i++){
//                buff+=temp.get(i).toString();
//            }

            session.getTransaction().commit();
            return "{" + gson.toJson(temp) + "}";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }

    }

    static public String allSurveys() {
        SurveyDAO surdao = new SurveyDAO();

        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Gson gson = new Gson();
            Criteria criteria = session.createCriteria(Survey.class);
            List<Survey> temp = criteria.list();

//            for(int i=0;i<temp.size();i++){
//                buff+=temp.get(i).toString();
//            }

            session.getTransaction().commit();
            return "{" + gson.toJson(temp) + "}";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }


    }

    static public boolean deleteById(int id) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            AnswerDAO ansdao = new AnswerDAO();
            SurveyDAO surdao = new SurveyDAO();
            Gson gson = new Gson();
            Criteria criteria = session.createCriteria(Answer.class);
            List<Answer> temp = criteria.add(Restrictions.eq("survey_id", id)).list();

            for (int i = 0; i < temp.size(); i++) {
                ansdao.delete(temp.get(i).getAnswer_id());
            }

            surdao.delete(id);


            session.getTransaction().commit();

            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);

        } finally {
            session.close();
            return false;
        }

    }

    static public String newAnswer(int id, int rating, int us_id) {
        AnswerDAO ansdao = new AnswerDAO();
        try {
            if (rating > 5 || rating < 0) {

                throw new IllegalArgumentException();
            }

            Gson gson = new Gson();
            Answer temp = new Answer();
            temp.setSurvey_id(id);
            temp.setRating(rating);
            temp.setUser_id(id);

            ansdao.add(temp);

            return gson.toJson(temp);

        } catch (IllegalArgumentException e) {
            return "Wrong rating";
        }


    }

    static public String getAnswer(int id) {
        AnswerDAO ansdao = new AnswerDAO();
        Gson gson = new Gson();

        Answer temp = ansdao.read(id);

        return gson.toJson(temp);
    }


    static public String changeRating(int id, int rating) {
        AnswerDAO ansdao = new AnswerDAO();
        Gson gson = new Gson();
        try {
            if (rating > 5 || rating < 0) {

                throw new IllegalArgumentException();
            }

            ansdao.update(id, rating);

            Answer temp = ansdao.read(id);

            return gson.toJson(temp);
        } catch (IllegalArgumentException e) {
            return "Wrong rating";
        }
    }

    static public String userStats(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            AnswerDAO ansdao = new AnswerDAO();
            SurveyDAO surdao = new SurveyDAO();

            Gson gson = new Gson();
//            Criteria criteria = session.createCriteria(Answer.class);
//            List<Answer> temp = criteria.add(Restrictions.eq("user_id", id)).list();
            Criteria criteria2 = session.createCriteria(Survey.class);

            List<Survey> tempSurv = criteria2.add(Restrictions.eq("user_id", id)).list();

            int surveysNumber = tempSurv.size();
            int[] average = new int[tempSurv.size()];
            int[] answNumber = new int[tempSurv.size()];

            for(int i=0;i<average.length;i++){
                average[i]=0;
                answNumber[i]=0;
            }

            Criteria criteriaAnsw = session.createCriteria(Answer.class);

            for(int i=0;i<tempSurv.size();i++){
                List<Answer> tempAnsw = criteriaAnsw.add(Restrictions.eq("survey_id", tempSurv.get(i).getSurvey_id())).list();

                if(tempAnsw.size()==0){
                    continue;
                }
                for(int j=0;j<tempAnsw.size();j++){
                    average[i]+=tempAnsw.get(j).getRating();
                }
                average[i]=average[i]/tempAnsw.size();
                answNumber[i]+=tempAnsw.size();
            }

            UserStats tempStats = new UserStats(surveysNumber,average,answNumber);




            session.getTransaction().commit();



            return gson.toJson(tempStats);

        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);

        } finally {
            session.close();
//            return null;
        }
    }

    static public String systemStats(){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            AnswerDAO ansdao = new AnswerDAO();
            SurveyDAO surdao = new SurveyDAO();

            Gson gson = new Gson();
            Criteria criteria = session.createCriteria(Answer.class);
            List<Answer> tempAnsw = criteria.list();
            Criteria criteria2 = session.createCriteria(Survey.class);
            List<Survey> tempSurv = criteria2.list();
            Criteria criteria3 = session.createCriteria(User.class);
            List<User> tempUsr = criteria3.list();

            int answerNumber = tempAnsw.size();
            int surveyNumber = tempSurv.size();
            int userNumber = tempUsr.size();



            float averageSurvToUsr = surveyNumber/userNumber;
            float averageAnswToSurv = answerNumber/surveyNumber;


            SystemStats tempstats = new SystemStats(averageAnswToSurv, averageSurvToUsr,surveyNumber,answerNumber,userNumber);




            return gson.toJson(tempstats);

        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);

        } finally {
            session.close();
//            return null;
        }

    }



}
