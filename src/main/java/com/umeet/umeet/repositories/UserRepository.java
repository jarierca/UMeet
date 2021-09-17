
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
    
    public List<User> findByUsernameContaining(String name);
    public List<User> findByNickNameContaining(String nickName);
    public Optional<User> findByUsername(String username);
}
