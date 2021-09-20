package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IFriendService;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
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
    @PostMapping("/friendsFilter")
    public String filterFriend(Model m, String username) {

        List<User> aux = userRepo.findByUsernameContaining(username);
        if (!aux.isEmpty()) {
            m.addAttribute("name", aux);
        }

        List<User> aux1 = userRepo.findByNickNameContaining(username);
        if (!aux1.isEmpty()) {
            m.addAttribute("nick", aux1);
        }

        return "friends/filteredFriends";
    }

    //Invite new user
    @GetMapping("/inviteUser")
    public String searchAllUsers(Model m) {

        return "/friends/inviteNewUser";
    }

    @PostMapping("/foundUser")
    public String userInvite(Model m, String username) {

        List<User> aux = userRepo.findByUsernameContaining(username);
        if (!aux.isEmpty()) {
            m.addAttribute("name", aux);
        }
        
        List<User> aux1 = userRepo.findByNickNameContaining(username);
        if (!aux.isEmpty()) {
            m.addAttribute("name", aux1);
        }

        /*Optional<User> aux2 = userRepo.findByUsername(username);//repositorio no pilla el id??
        if (!aux1.isEmpty()) {
            m.addAttribute("idFriend", aux2);
        }
*/
        return "friends/searchResultFriends";
    }

    @GetMapping("/addFriend")
    public String addUser(Long idUser) {
        User user2=userRepo.findById(idUser).get();
        User user1=userRepo.findById(1L).get();
        Friend friend=new Friend(null, "Invitado", user1, user2);
        friendRepo.save(friend);
        
        return "friends/friendsList";
    }

}
