
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    
   public List<Message> findMessageByChannel(Channel channel);
    
}
