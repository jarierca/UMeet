package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IFriendService;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friends")

public class FriendController {

    @Autowired
    private FriendRepository friendRepo;

    @Autowired
    private UserRepository userRepo;

    
//List friends
    @GetMapping("/friendsList") //Va la vista poniendo detras ?idUsuario=1 (http://localhost:8090/friends/friendsList?idUsuario=3)
    public String listFriends(Model m, Long idUsuario) {
        

        m.addAttribute("friendsAccepted", friendRepo.findByAmigos(idUsuario, "Aceptado"));
        m.addAttribute("friendsPending", friendRepo.findByAmigos(idUsuario, "Invitado"));
        
        return "friends/view";
    }

//Filter friends    
    /*@PostMapping("/friendsFilter")
    public String filterFriend(Model m, Long idUsuario) {

        m.addAttribute("filterUserName", friendRepo.findByUserNameContaining(idUsuario, "Aceptado1" ));
        m.addAttribute("filterNickName", friendRepo.findByNickNameContaining(idUsuario, "Aceptado2"));
        
        //List<User> aux = friendRepo.findByUserNameContaining(user);
        //if (!aux.isEmpty()) {
        //    m.addAttribute("filterUserName", aux);
        //}
        /*List<User> aux1 = friendRepo.findByNickNameContaining(user);
        if (!aux1.isEmpty()) {
            m.addAttribute("filterNickName", aux1);
        }

        return "friends/filter";
    }*/

//Search all users
    /*@GetMapping("/userList")
    public String searchAllUsers(Model m, Long userId) {

        m.addAttribute("user", userRepo.findById(userId).get());

        return "/friends/allUsers";
    }

   
//Invite user
    @PostMapping
    public String inviteUser(Model m, Long userId) {
        m.addAttribute("invite", userRepo.)
    }*/
}
