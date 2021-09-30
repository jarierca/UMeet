package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.ListasFriendDto;
import com.umeet.umeet.entities.User;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "feignFriends", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/friends")

public interface FriendFeign {

    @PostMapping("/friendsList") 
    public ListasFriendDto listFriends(@RequestParam Long idUser);
    
    @PostMapping("/friendsFilter")
    public List<User> filterFriend(@RequestParam String username, @RequestParam Long idUser);

    @PostMapping("/foundUser")
    public List<User> userInvite(@RequestParam String username, @RequestParam Long idUser);

    @PostMapping("/addFriend")
    public List<User> addUser(@RequestParam Long idUser, @RequestParam Long id, @RequestParam Long idUserFriend);

    @PostMapping("/accept")
    public User accept(@RequestParam Long idUser, @RequestParam Long idUserFriend);

    @GetMapping("/removeFriend")
    public void remove(@RequestParam Long idUser, @RequestParam Long idFriend);
}
