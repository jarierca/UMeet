package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    public List<Friend> findFriendByUser1(User user);

    public List<Friend> findFriendByUser2(User user);

    public List<Friend> deleteByUser1(Long id);

    public List<Friend> deleteByUser2(Long id);
        
    
    
    
    
    
   

    //Filtrar por nombre de usuario y por nick
    /*@Query("select f.user2 from Friend f where f.user1.id = :idUsuario  and f.status = :estado")
    public List<User> findByUserNameContaining(Long idUsuario, String estado);
    
    @Query("select f.user2 from Friend f where f.user1.id = :idUsuario  and f.status = :estado")
    public List<Friend> findByNickNameContaining(Long idUsuario, String nickName);
*/

    @Query("select f.user2 from Friend f where f.user1.id = :idUsuario  and f.status = :estado")
    public List<User> findByAmigos(Long idUsuario, String estado);
    //public List <Friend> findByUser2(Long idUser2);
    
}
