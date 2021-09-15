
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Integer> {
    
   
    
}
