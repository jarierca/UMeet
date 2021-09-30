package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.ListasFriendDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.feign.FriendFeign;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/friends")

public class FriendController {

    @Autowired
    private FriendFeign friendFeign;

    @Autowired
    private FriendRepository friendRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;
//List friends

    @GetMapping("/friendsList") //Va la vista poniendo detras ?idUser=1 (http://localhost:8090/friends/friendsList?idUser=3)
    public String listFriends(Model m) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ListasFriendDto listita = friendFeign.listFriends(u.getId());

        m.addAttribute("friendsAccepted", listita.getFriendsAccepted());
        m.addAttribute("friendsPendingEnviado", listita.getFriendsPendingEnviado());
        m.addAttribute("friendsPendingRecibido", listita.getFriendsPendingRecibido());

        return "friends/view";
    }

//Filter friends    
    @PostMapping("/friendsFilter")
    public String filterFriend(Model m, String username) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<User> filtrados = friendFeign.filterFriend(username, u.getId());

        m.addAttribute("name", filtrados);

        return "friends/filteredFriends";
    }

    //Invite new user
    @GetMapping("/inviteUser")
    public String searchAllUsers(Model m) {

        return "friends/inviteNewUser";
    }

    @PostMapping("/foundUser")
    public String userInvite(Model m, String username) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

       List <User> encontrados = friendFeign.userInvite(username, u.getId());

        m.addAttribute("name", encontrados);

        return "friends/searchResultFriends";
    }

    @PostMapping("/addFriend")
    public String addUser(Model m, Friend friend, Long idUserFriend) {
        /*if (idUser == null) {
            return "redirect:profile/logout";
        }*/
        
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

       List<User> addUser = friendFeign.addUser(u.getId(), friend.getId(), idUserFriend);

        m.addAttribute("friendsPending", addUser);

        return "redirect:friendsList";
    }

    @PostMapping("/accept")
    public String accept(Model m, Long idUserFriend) {

        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        friend.setUser1(userRepo.findById(u.getId()).get());
//        friend.setUser2(userRepo.findById(idUserFriend).get());

        User user = userRepo.findById(idUserFriend).get();
        User user2 = userRepo.findById(u.getId()).get();

        Friend f1 = friendRepo.findByUser1AndUser2(user, user2);

        f1.setStatus("aceptado");

        friendRepo.save(f1);

        return "redirect:/home";
    }

    @GetMapping("/removeFriend")
    public String remove(Long idFriend) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

       friendFeign.remove(u.getId(), idFriend);
       
        return "redirect:friendsList";
    }

}
