
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/b")
public class AccessController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private FriendRepository friendRepo;
    
    @Autowired
    private UserServerRoleRepository usrRepo;

    @Autowired
    private ModelMapper mapper;
    
    @GetMapping("/login")
    public String login(){ 
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:home";
        }else{
            return "login";
        }
    }
    
//    @GetMapping("/register")
//    public String register(Model m){
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal() != "anonymousUser") {
//            return "redirect:home";
//        }else{
//            m.addAttribute("user", new User());
//            return "register";
//        }
//    }
    
    @GetMapping("/info")
    @ResponseBody
    public String info(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null){
            return auth.getName() + auth.getDetails().toString();
        }else{
            return "Nadie";
        }
    } 
    
    @PostMapping("/newregister")
    public Boolean newregister(UserDto userDto){
        Optional<User> usuarios = userRepository.findByUsername(userDto.getUsername());
        if (!usuarios.isPresent()) {

            userDto.setNickName(userDto.getUsername());
            userDto.setAvatar("C:/zzUpload/avatar/avatar-stock.png");
            userDto.setStatus("desconectado");

            User user = mapper.map(userDto, User.class);
            userRepository.save(user);

            return true;
        }else{
            return false;
        }
    }
    
    @GetMapping("/home")
    public String index(HttpServletResponse response , Model m){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser"){
            
            String username = auth.getName(); 
            UserValidacionDto u = (UserValidacionDto) auth.getPrincipal();
            Optional<User> user = userRepository.findByUsername(username);              //Obtenemos lista de amigos e invitado para index
            m.addAttribute("friendsAccepted", friendRepo.findByAmigos(u.getId(), "aceptado"));
            m.addAttribute("friendsPending", friendRepo.findByAmigos(u.getId(), "invitado"));

            List<UserServerRole> usrAux = usrRepo.findByUser(user.get());               //Obtenemos lista de servidores por usuario
            List<Server> servers = usrAux.stream().map(x-> x.getServer()).collect(Collectors.toList());
            m.addAttribute("userServers",servers);
            
        }
        
        return "index";
    }
    
    @GetMapping("/log-out")
    public String logoOut(HttpServletResponse response){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser"){
            String username = auth.getName();
            User user = userRepository.findByUsername(username).get();
            user.setStatus("desconectado");
            userRepository.save(user);
        } 
        System.out.println("\n\nEBNTRAZQA\n\n");
        
        logout();
        return "redirect:logout";
    }
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}
