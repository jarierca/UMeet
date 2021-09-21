package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    
    public List <Server> findByDescriptionContaining(String description);
    public List <Server> findByNameContaining(String name);
   
    
    
    
    
}
