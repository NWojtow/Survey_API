package sample.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.HibernateFactory;
import sample.entity.Survey;
import sample.entity.User;

public class SurveyDAO {
    public void add(Survey survey){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(survey);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }


    public Survey read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try {
            Survey survey = (Survey) session.get(Survey.class, id);
            return survey;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

//    public static void update(long id, User newUser) {
//        HibernateFactory hibernateFactory = new HibernateFactory();
//        Session session = hibernateFactory.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            User user = (User) session.find(User.class, id);
//            car.setProductionDate(newCar.getProductionDate());
//            car.setMark(newCar.getMark());
//            session.getTransaction().commit();
//        } catch (Exception ex) {
//            transaction.rollback();
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        } finally {
//            session.close();
//        }
//    }

    public static void delete(int id) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Survey survey = (Survey) session.get(Survey.class, id);
            session.delete(survey);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }
}


