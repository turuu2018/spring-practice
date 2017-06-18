
package com.practice.springpractice.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author PDBD
 */

@Embeddable
public class UserRoleId implements java.io.Serializable{
    
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "role", length = 50, nullable = false)
    private String role;

    public UserRoleId(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }


    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRoleId() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRoleId other = (UserRoleId) obj;
        
     
         
         return (this.getUserId() == other.getUserId() && Objects.equals(this.getRole(), other.getRole()));
        
    }
    
}


