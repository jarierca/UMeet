package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.interfaces.IFriendService;
import com.umeet.umeet.repositories.FriendRepository;
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
    private IFriendService service;

    @Autowired
    private FriendRepository friendRepo;

//List friends
    @GetMapping("friendsList") //Va la vista poniendo detras ?idUsuario=1 (http://localhost:8090/friends/friendsList?idUsuario=3)
    public String listFriends(Model m, Long idUsuario) {

        /*List <Friend> aux=friendRepo.findByUser1(idUsuario, );
        if(!aux.isEmpty()){
            List <Friend> aux2=friendRepo.findByUser2(idUsuario);
            if(!aux2.isEmpty()){
                aux.addAll(aux2);
            }
        }*/
        m.addAttribute("friendsAccepted", friendRepo.findByAmigos(idUsuario, "Aceptado"));
        m.addAttribute("friendsPending", friendRepo.findByAmigos(idUsuario, "Invitado"));
        return "friends/view";
    }

//Filter friends
    @GetMapping("/friendsList")
    
    
    @PostMapping("/friendsFilter")
    public String filterFriend(Model m, String username) {
        
        List<Friend> aux = friendRepo.findByUserNameContaining(username);
        if (!aux.isEmpty()) {
            m.addAttribute("filterName", aux);
        }
        List<Friend> aux1 = friendRepo.findByNickNameContaining(username);
        if (!aux.isEmpty()) {
            m.addAttribute("filterName", aux);
        }
        /*List<Friend>
        if (!user.isEmpty()) {
            m.addAttribute("filterUserName", friendRepo.findByUserNameContaining(username));
        } else {
            m.addAttribute("filterNicknName", friendRepo.findByNickNameContaining(nickName));
        }*/
        return "friends/filter";
    }
//Invite friends (este igual va en el users??)
}
