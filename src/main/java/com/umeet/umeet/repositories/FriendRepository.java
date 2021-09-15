package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepository extends JpaRepository<Friend, Long>{

    
    @Query("select f.user2 from Friend f where f.user1.id = :idUsuario  and f.status = :estado")
    public List <User> findByAmigos(Long idUsuario, String estado);
    //public List <Friend> findByUser2(Long idUser2);
    
}
