
package com.practice.springpractice.controller.account;

import com.practice.springpractice.dao.UserDao;
import com.practice.springpractice.form.RegisterForm;
import com.practice.springpractice.model.User;
import com.practice.springpractice.model.UserRole;
import com.practice.springpractice.model.UserRoleId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class RegisterController {
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao UserDao;
    
    @Autowired
    
    private MessageSource messageSource;

      @Autowired
    private PasswordEncoder passwordEncoder;
     @Autowired
     private UserDetailsService UserDetailsService;
    @ModelAttribute
    public RegisterForm initRegisterForm(){
        return new RegisterForm();
    }
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute RegisterForm registerForm) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("registerForm", registerForm);
        return mav;
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView registerPost(@ModelAttribute @Valid RegisterForm registerForm, 
            BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        ModelAndView mav;
        if (!Objects.equals(registerForm.getPassword1(), registerForm.getPassword2())){
            bindingResult.reject("password2", "Passwords do not match!");
            
        }
        if (UserDao.get(registerForm.getUsername()) != null) {
           bindingResult.reject("username", messageSource.getMessage("username.exist", null, locale));
    }
        
        if (bindingResult.hasErrors()){
            mav = register(registerForm);
            
        } else {  // register user
            Session session = null; 
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                User user = new User();
                user.setUsername(registerForm.getUsername());
             user.setPassword(passwordEncoder.encode(registerForm.getPassword1()));
                user.setRegisteredDate(new Date());
                user.setLastName(registerForm.getLastName());
                user.setFirstName(registerForm.getFirstName());
          
                session.save(user);
                UserRole userRole = new UserRole();
                userRole.setId(new UserRoleId(user.getId(), "ROLE_USER"));
                userRole.setUser(user);
                session.save(userRole);
                
                transaction.commit();
            mav = new ModelAndView("register-activate");
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
            }
                bindingResult.reject("username", "Database error");
                mav = register(registerForm);
                
        }
            finally {
                if (session != null ) {
                    try {
                        session.close();
                    } catch (HibernateException e) {
                    }
                }
            }
        }
         return mav;       
}
}
