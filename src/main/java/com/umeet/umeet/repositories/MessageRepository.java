
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    public List<Message> findByChannel(Channel channel);

    public List<Message> findByUser(User user);

    public List<Message> findByUserDestiny(User userDestiny);
    
}
