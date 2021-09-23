package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.services.FriendService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired

    private FriendService friendService;
//List friends

    @GetMapping("/friendsList") //Va la vista poniendo detras ?idUser=1 (http://localhost:8090/friends/friendsList?idUser=3)
    public String listFriends(Model m) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<User> aceptados = friendService.sendFriendList(u.getId());

        List< User> invitados = friendRepo.findByAmigos(u.getId(), "Invitado").stream()
                .distinct()
                .collect(Collectors.toList());

        m.addAttribute("friendsAccepted", aceptados);
        m.addAttribute("friendsPending", invitados);

        return "friends/view";
    }

//Filter friends    
    @PostMapping("/friendsFilter")
    public String filterFriend(Model m, String username) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<User> aux2 = null;
        if (username == null || ("").equals(username)) {
            aux2 = friendService.sendFriendList(u.getId());

        } else {
            List<User> aux = userRepo.findByUsernameContaining(username);

            List<User> aux1 = userRepo.findByNickNameContaining(username);

            aux2 = Stream.concat(aux.stream(), aux1.stream())
                    .distinct()
                    .collect(Collectors.toList());

        }

        m.addAttribute("name", aux2);

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
        }

        List<User> aux1 = userRepo.findByNickNameContaining(username);
        if (!aux1.isEmpty()) {
        }
        List<User> aux2 = Stream.concat(aux.stream(), aux1.stream())
                .distinct()
                .collect(Collectors.toList());

        m.addAttribute("name", aux2);

        /*Optional<User> aux2 = userRepo.findByUsername(username);//repositorio no pilla el id??
        if (!aux1.isEmpty()) {
            m.addAttribute("idFriend", aux2);
        }
         */
        return "friends/searchResultFriends";
    }

    @PostMapping("/addFriend")
    public String addUser(Model m, Friend friend, Long idUserFriend) {
        /*if (idUser == null) {
            return "redirect:/profile/logout";
        }*/

        friend.setStatus("invitado");

        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        friend.setUser1(userRepo.findById(u.getId()).get());
        friend.setUser2(userRepo.findById(idUserFriend).get());

        friendRepo.save(friend);

        List<User> amigos = friendRepo.findByAmigos((idUserFriend), "invitado");

        m.addAttribute("friendsPending", amigos);

        return "redirect:friendsList";
    }
    
    
   
    
    
    

}
