
package com.practice.springpractice.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author PDBD
 */
public class PasswordEncoderTest {
    @Test
    
            
    public void encrypt(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "test";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("pass:" + encodedPassword);
        Assert.assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
    
}

