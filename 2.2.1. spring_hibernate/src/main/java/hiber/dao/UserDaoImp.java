package hiber.dao;

import hiber.model.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      String hql = "FROM User where car.model = :model and car.series = :series";
      Query query = sessionFactory
              .getCurrentSession()
              .createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return (User) query.getSingleResult();
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().persist(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory
              .getCurrentSession()
              .createQuery("from User", User.class)
              .getResultList();
   }

}
