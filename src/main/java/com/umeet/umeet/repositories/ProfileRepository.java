
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);

}
