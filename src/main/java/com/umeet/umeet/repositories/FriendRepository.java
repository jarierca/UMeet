
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendRepository extends JpaRepository<Friend, Long>{
     public List<Friend> findFriendByUser1(User user);
     public List<Friend> findFriendByUser2(User user);
     public List<Friend> deleteByUser1(Long id);
     public List<Friend> deleteByUser2(Long id);
}
