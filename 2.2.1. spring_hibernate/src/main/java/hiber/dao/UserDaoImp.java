package hiber.dao;

import hiber.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   private static final String USER_BY_MODEL_AND_SERIES =
           "FROM User where car.model = :model and car.series = :series";

   private static final String GET_ALL_FROM_USERS =
           "FROM User";

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      Query query = entityManager.createQuery(USER_BY_MODEL_AND_SERIES, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return (User) query.getSingleResult();
   }

   @Override
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   public List<User> getAllUsersList() {
      return entityManager
              .createQuery(GET_ALL_FROM_USERS, User.class)
              .getResultList();
   }

}
