package sample.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.HibernateFactory;
import sample.entity.User;

public class UserDAO {

    public void add(User user){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }


    public User read(int id){
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        try {
            User user = (User) session.get(User.class, id);
            return user;
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
            User user = (User) session.get(User.class, id);
            session.delete(user);
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




