/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.springpractice.controller;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author PDBD
 */
@Controller
public class ErrorController {
    
     private static final Logger LOGGER = Logger.getLogger(ErrorController.class.getName());
   

    @RequestMapping(value = { "/error"}, method = RequestMethod.GET)
    public String index() {
        LOGGER.info(">>> error controller");
   
       
        return "error";
    }
    
}
