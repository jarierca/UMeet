
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {
    
    public List<Message> findByChannel(Long idChannel);

    public List<Message> findByUser(Long idUser);
    
}
