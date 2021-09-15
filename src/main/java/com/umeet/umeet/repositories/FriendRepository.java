
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendRepository extends JpaRepository<Friend, Long>{
     //public List<Friend> findFriendByUser(User user);
}
