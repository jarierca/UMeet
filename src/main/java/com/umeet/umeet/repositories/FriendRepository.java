/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author skudo
 */
public interface FriendRepository extends JpaRepository<Friend, Long>{
    
}
