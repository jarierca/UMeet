/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aran
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    public List<User> findByUsernameContaining(String name);
    public List<User> findByNickNameContaining(String nickName);
   
    
}
