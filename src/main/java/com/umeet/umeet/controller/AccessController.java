package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.feign.EmailFeign;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
    private EmailFeign emailFeign;

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:home";
        } else {
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:home";
        } else {
            m.addAttribute("user", new User());
            return "register";
        }
    }

    @GetMapping("/info")
    @ResponseBody
    public String info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName() + auth.getDetails().toString();
        } else {
            return "Nadie";
        }
    }

    @PostMapping("/newregister")
    public String newregister(Model m, User user) {
        Optional<User> usuarios = userRepository.findByUsername(user.getUsername());
        if (!usuarios.isPresent()) {

            //Restringe si la pass del user es mayor de 8 pero el error que muestra es el otro
//            if(user.getPass().length() < 8){
//                 m.addAttribute("error","La contraseña es demasiado corta");
//                return "register";
//            }else{
            String encodedPassword = passwordEncoder.encode(user.getPass());
            user.setPass(encodedPassword);

            user.setNickName(user.getUsername());
            user.setAvatar("C:/zzUpload/avatar/avatar-stock.png");
            user.setStatus("desconectado");

            userRepository.save(user);
            
            String txt = "Hola " + user.getUsername() + ",</br> te damos las gracias por unirte a nuestra comunidad de U-Meet, en la que te permite hablar y con tus amigos.";
            //Enviar el email
            emailFeign.mail(user.getEmail(), "¡Bienvenido a U-Meet!", txt);
//            } 

            return "login";
        } else {
            m.addAttribute("error", "El usuario que has introducido no esta disponible");
            return "register";
        }
    }

    @GetMapping("/home")
    public String index(HttpServletResponse response, Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {

            String username = auth.getName();
            UserValidacionDto u = (UserValidacionDto) auth.getPrincipal();
            Optional<User> user = userRepository.findByUsername(username);              //Obtenemos lista de amigos e invitado para index
            m.addAttribute("friendsAccepted", friendRepo.findByAmigos(u.getId(), "aceptado"));
            m.addAttribute("friendsPending", friendRepo.findByAmigos(u.getId(), "invitado"));

            List<UserServerRole> usrAux = usrRepo.findByUser(user.get());               //Obtenemos lista de servidores por usuario
            List<Server> servers = usrAux.stream().map(x -> x.getServer()).collect(Collectors.toList());
            m.addAttribute("userServers", servers);
        }

        return "index";
    }

    @GetMapping("/log-out")
    public String logoOut(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
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
    public String logout() {
        return "login";
    }

}
