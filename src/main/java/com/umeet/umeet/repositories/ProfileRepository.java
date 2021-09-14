
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<User, Integer>{
    
}
