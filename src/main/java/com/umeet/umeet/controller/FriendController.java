package com.umeet.umeet.controller;
import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.services.FriendService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
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
    
    @Autowired
    private ModelMapper mapper;
//List friends

    @GetMapping("/friendsList") //Va la vista poniendo detras ?idUser=1 (http://localhost:8090/friends/friendsList?idUser=3)
    public String listFriends(Model m) {
        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

//        List<User> aceptados = friendService.sendFriendList(u.getId());
        
        List<UserDto> acepta1 = friendRepo.findAll().stream()
                .filter(x->x.getStatus().equalsIgnoreCase("aceptado") && x.getUser1().getUsername().equalsIgnoreCase(u.getUsername()))
                .map(x->mapper.map(x.getUser2(),UserDto.class ))
                .collect(Collectors.toList());
        
        List<UserDto> acepta2 = friendRepo.findAll().stream()
                .filter(x->x.getStatus().equalsIgnoreCase("aceptado") && x.getUser2().getUsername().equalsIgnoreCase(u.getUsername()))
                .map(x->mapper.map(x.getUser1(),UserDto.class ))
                .collect(Collectors.toList());
        
        
        acepta1.addAll(acepta2);

//        List< User> invitados = friendRepo.findByAmigos(u.getId(), "Invitado").stream()
//                .distinct()
//                .collect(Collectors.toList());
        List<Friend> aux = friendRepo.findByUser1(userRepo.findById(u.getId()).get());
        List<Friend> aux2 = friendRepo.findByUser2(userRepo.findById(u.getId()).get());
        
        List<UserDto> aceptadosUser1 = aux.stream()
                .filter(x->x.getUser1().getUsername().equalsIgnoreCase(u.getUsername()) && !x.getStatus().equalsIgnoreCase("aceptado"))
                .map(x->mapper.map(x.getUser2(), UserDto.class))
                .collect(Collectors.toList());
        
        List<UserDto> aceptadosUser2 = aux2.stream()
                .filter(x->x.getUser2().getUsername().equalsIgnoreCase(u.getUsername()) && !x.getStatus().equalsIgnoreCase("aceptado"))
                .map(x->mapper.map(x.getUser1(), UserDto.class))
                .collect(Collectors.toList());

        
        m.addAttribute("friendsAccepted", acepta1);
        
        m.addAttribute("friendsPendingEnviado", aceptadosUser1);
        m.addAttribute("friendsPendingRecibido", aceptadosUser2);

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
            
            List<Friend> friend1 = friendRepo.findByUser1AndStatus(userRepo.getById(u.getId()),"aceptado");
            List<Friend> friend2 = friendRepo.findByUser2AndStatus(userRepo.getById(u.getId()),"aceptado");
            
//            friend1.addAll(friend2);
            List<User> yo = friend1.stream()
                    .map(x->x.getUser2())
                    .collect(Collectors.toList());
            
            List<User> yo2 = friend2.stream()
                    .map(x->x.getUser1())
                    .collect(Collectors.toList());
            
            
            yo.addAll(yo2);
            
            aux2 = yo.stream()
                    .filter(x->x.getUsername().toLowerCase().indexOf(username.toLowerCase())!= -1 || x.getNickName().toLowerCase().indexOf(username.toLowerCase()) != -1)
                    .collect(Collectors.toList());
        }

        m.addAttribute("name", aux2);

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
 
        List<User> aux = userRepo.findByUsernameContaining(username);
        if (!aux.isEmpty()) {
        }

        List<User> aux1 = userRepo.findByNickNameContaining(username);
        if (!aux1.isEmpty()) {
        }
        
        List<User> aux3 = Stream.concat(aux.stream(), aux1.stream())
                .filter(x-> !x.getId().equals(u.getId()))
                .distinct()
                .collect(Collectors.toList());
        
        

        List<Friend> friend1 = friendRepo.findByUser1(userRepo.getById(u.getId()));
        List<Friend> friend2 = friendRepo.findByUser2(userRepo.getById(u.getId()));
        
        List<User> yo = friend1.stream()
                    .map(x->x.getUser2())
                    .collect(Collectors.toList());
            
            List<User> yo2 = friend2.stream()
                    .map(x->x.getUser1())
                    .collect(Collectors.toList());
        
        yo.addAll(yo2);
            
        List<User>  aux2 = aux3.stream()
                    .filter(x->yo.contains(x)==false )
                    .collect(Collectors.toList());
              
        
        m.addAttribute("name", aux2);

        return "friends/searchResultFriends";
    }

    @PostMapping("/addFriend")
    public String addUser(Model m, Friend friend, Long idUserFriend) {
        /*if (idUser == null) {
            return "redirect:profile/logout";
        }*/

        friend.setStatus("invitado");

        UserValidacionDto u = (UserValidacionDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        friend.setUser1(userRepo.findById(u.getId()).get());
        friend.setUser2(userRepo.findById(idUserFriend).get());

        friendRepo.save(friend);

//        List<Friend> aux = friendRepo.findByUser1OrUser2(userRepo.findById(u.getId()).get());
        List<User> amigos = friendRepo.findByAmigos((idUserFriend), "invitado");

        m.addAttribute("friendsPending", amigos);

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

        return "redirect:home";
    }
     
    
    @GetMapping("/removeFriend")
    public String remove(Long idFriend){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        List<Friend> relacionAmigo = friendRepo.findAll();
        
        Optional<Friend> amigo = relacionAmigo.stream()
                .filter(x->x.getUser1().getId().equals(u.getId()) && x.getUser2().getId().equals(idFriend) || x.getUser1().getId().equals(idFriend) && x.getUser2().getId().equals(u.getId()) )
                .findFirst();
        
        friendService.removeFriend(amigo.get());
        return "redirect:friendsList";
    }  

}