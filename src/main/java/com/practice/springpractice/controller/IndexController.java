
package com.practice.springpractice.controller;

import com.practice.springpractice.dao.UserDao;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author PDBD
 */


@Controller
public class IndexController {
    
     private static final Logger LOGGER = Logger.getLogger(IndexController.class.getName());
   
      @Autowired
    private UserDao UserDao;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
          LOGGER.info(">>>> index controller");
        ModelAndView mav = new ModelAndView("index");
        
        mav.addObject("users", UserDao.list(null) );
      
        return mav;
                
    }
    
}
