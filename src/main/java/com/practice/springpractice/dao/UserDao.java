
package com.practice.springpractice.dao;

import com.practice.springpractice.model.User;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PDBD
 */

@Repository
public class UserDao {
     private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
     
     @Autowired 
     private SessionFactory sessionFactory;
     
     public User get(Integer id ){
          
         try {
             Session session = sessionFactory.getCurrentSession();
             Criteria crit = session.createCriteria(User.class);
             crit.add(Restrictions.eq("id",id));
             List<User> users = crit.list();
             if(users.size()== 1 ){
                 return users.get(0);
             }
         } catch (HibernateException e) {
             LOGGER.severe(e.getMessage());
             
         }
      return null;           
     }
    
 public User get(String username ){
         
         try {
             Session session = sessionFactory.getCurrentSession();
             Criteria crit = session.createCriteria(User.class);
             crit.add(Restrictions.eq("username",username));
             List<User> users = crit.list();
             if(users.size()== 1 ){
                 return users.get(0);
             }
         } catch (HibernateException e) {
             LOGGER.severe(e.getMessage());
             
         }
      return null;           
     }
         

     
 public List<User> list(Boolean active ){
          
         try {
             Session session = sessionFactory.getCurrentSession();
             Criteria crit= session.createCriteria(User.class);
             
             if(active != null){
                crit.add(Restrictions.eq("active", active));
                
             }
                return crit.list();
                
             
         } catch (HibernateException e) {
             LOGGER.severe(e.getMessage());
             
         }
      return null;           
     }


    
}
