package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.ListasFriendDto;
import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.services.FriendService;
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
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/friends")
@RestController

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

    @PostMapping("/friendsList") //Va la vista poniendo detras ?idUser=1 (http://localhost:8090/friends/friendsList?idUser=3)
    public ListasFriendDto listFriends(Long idUser) {
        User u = userRepo.findById(idUser).get();

        List<UserDto> acepta1 = friendRepo.findAll().stream()
                .filter(x -> x.getStatus().equalsIgnoreCase("aceptado") && x.getUser1().getUsername().equalsIgnoreCase(u.getUsername()))
                .map(x -> mapper.map(x.getUser2(), UserDto.class))
                .collect(Collectors.toList());

        List<UserDto> acepta2 = friendRepo.findAll().stream()
                .filter(x -> x.getStatus().equalsIgnoreCase("aceptado") && x.getUser2().getUsername().equalsIgnoreCase(u.getUsername()))
                .map(x -> mapper.map(x.getUser1(), UserDto.class))
                .collect(Collectors.toList());

        acepta1.addAll(acepta2);

        List<Friend> aux = friendRepo.findByUser1(userRepo.findById(u.getId()).get());
        List<Friend> aux2 = friendRepo.findByUser2(userRepo.findById(u.getId()).get());

        List<UserDto> aceptadosUser1 = aux.stream()
                .filter(x -> x.getUser1().getUsername().equalsIgnoreCase(u.getUsername()) && !x.getStatus().equalsIgnoreCase("aceptado"))
                .map(x -> mapper.map(x.getUser2(), UserDto.class))
                .collect(Collectors.toList());

        List<UserDto> aceptadosUser2 = aux2.stream()
                .filter(x -> x.getUser2().getUsername().equalsIgnoreCase(u.getUsername()) && !x.getStatus().equalsIgnoreCase("aceptado"))
                .map(x -> mapper.map(x.getUser1(), UserDto.class))
                .collect(Collectors.toList());

        ListasFriendDto listas = new ListasFriendDto(acepta1, aceptadosUser1, aceptadosUser2);

        return listas;
    }

//Filter friends    
    @PostMapping("/friendsFilter")
    public List<User> filterFriend(String username, Long idUser) {

        User u = userRepo.findById(idUser).get();

        List<User> aux2 = null;
        if (username == null || ("").equals(username)) {
            aux2 = friendService.sendFriendList(u.getId());

        } else {

            List<Friend> friend1 = friendRepo.findByUser1AndStatus(userRepo.getById(u.getId()), "aceptado");
            List<Friend> friend2 = friendRepo.findByUser2AndStatus(userRepo.getById(u.getId()), "aceptado");

            List<User> yo = friend1.stream()
                    .map(x -> x.getUser2())
                    .collect(Collectors.toList());

            List<User> yo2 = friend2.stream()
                    .map(x -> x.getUser1())
                    .collect(Collectors.toList());

            yo.addAll(yo2);

            aux2 = yo.stream()
                    .filter(x -> x.getUsername().toLowerCase().indexOf(username.toLowerCase()) != -1 || x.getNickName().toLowerCase().indexOf(username.toLowerCase()) != -1)
                    .collect(Collectors.toList());
        }

        return aux2;
    }

    @PostMapping("/foundUser")
    public List<User> userInvite(String username, Long idUser) {

        User u = userRepo.findById(idUser).get();

        List<User> aux = userRepo.findByUsernameContaining(username);
        if (!aux.isEmpty()) {
        }

        List<User> aux1 = userRepo.findByNickNameContaining(username);
        if (!aux1.isEmpty()) {
        }

        List<User> aux3 = Stream.concat(aux.stream(), aux1.stream())
                .filter(x -> !x.getId().equals(u.getId()))
                .distinct()
                .collect(Collectors.toList());

        List<Friend> friend1 = friendRepo.findByUser1(userRepo.getById(u.getId()));
        List<Friend> friend2 = friendRepo.findByUser2(userRepo.getById(u.getId()));

        List<User> yo = friend1.stream()
                .map(x -> x.getUser2())
                .collect(Collectors.toList());

        List<User> yo2 = friend2.stream()
                .map(x -> x.getUser1())
                .collect(Collectors.toList());

        yo.addAll(yo2);

        List<User> aux2 = aux3.stream()
                .filter(x -> yo.contains(x) == false)
                .collect(Collectors.toList());

        return aux2;
    }

    @PostMapping("/addFriend")
    public List<User> addUser(Long idUser, Friend friend, Long idUserFriend) {
        User u = userRepo.findById(idUser).get();

        friend.setStatus("invitado");

        friend.setUser1(userRepo.findById(u.getId()).get());
        friend.setUser2(userRepo.findById(idUserFriend).get());

        friendRepo.save(friend);

        List<User> amigos = friendRepo.findByAmigos((idUserFriend), "invitado");

        return amigos;

    }

    @PostMapping("/accept")
    public User accept(Long idUser, Long idUserFriend) {
        User u = userRepo.findById(idUser).get();
        
        User user = userRepo.findById(idUserFriend).get();
        User user2 = userRepo.findById(u.getId()).get();
        Friend f1 = friendRepo.findByUser1AndUser2(user, user2);

        f1.setStatus("aceptado");

        friendRepo.save(f1);

        return user;

        // return "redirect:home";
    }

    @GetMapping("/removeFriend")
    public void remove(Long idUser,Long idFriend) {
        User u = userRepo.findById(idUser).get();
      

        List<Friend> relacionAmigo = friendRepo.findAll();

        Optional<Friend> amigo = relacionAmigo.stream()
                .filter(x -> x.getUser1().getId().equals(u.getId()) && x.getUser2().getId().equals(idFriend) || x.getUser1().getId().equals(idFriend) && x.getUser2().getId().equals(u.getId()))
                .findFirst();

        friendService.removeFriend(amigo.get());
       
    }

}
