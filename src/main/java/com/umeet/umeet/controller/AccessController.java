package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.feign.*;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailFeign emailFeign;

    @Autowired
    private AccesFeign accesFeign;

    @Autowired
    private ProfileFeign profileFeign;

    @Autowired
    private UserServerRoleFeign usrFeign;

    @Autowired
    private FriendFeign friendFeign;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AuthenticationManager authMan;

    @GetMapping("/verUsuario")
    @ResponseBody
    public Map<String,Object> info(@AuthenticationPrincipal OAuth2User persona){
        return persona.getAttributes();
    }    
    
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:home";
        } else {
            return "login";
        }
    }
    
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User user ) {
        //Paso 1. Si no esta el usuario se da de alta
        //Paso 2. Hacer login.
        
        //Login con github
        if(user.getAttribute("url").equals("https://api.github.com/users/"+user.getAttribute("login"))){
            System.out.println(""+user.getAttribute("login"));
            UsernamePasswordAuthenticationToken u=new UsernamePasswordAuthenticationToken(user.getAttribute("login"), "2");
            Authentication auth = authMan.authenticate(u);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:home";
        }else{
            return "redirect:home";
        }   
    }

    @GetMapping("/register")
    public String register(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            return "redirect:home";
        } else {
            m.addAttribute("user", new UserDto());
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
    public String newregister(Model m, UserDto user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        Boolean register = accesFeign.newRegister(user);
        if (register) {
            String txt = "Hola " + user.getUsername() + ",</br> te damos las gracias por unirte a nuestra comunidad de U-Meet, en la que te permite hablar y con tus amigos.";
            emailFeign.mail(user.getEmail(), "¡Bienvenido a U-Meet!", txt);
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
            UserDto user = profileFeign.getUserByUsername(username);              //Obtenemos lista de amigos e invitado para index
            m.addAttribute("friendsAccepted", friendFeign.getUsersByFriends(u.getId(), "aceptado"));
            m.addAttribute("friendsPending", friendFeign.getUsersByFriends(u.getId(), "invitado"));

            List<UserServerRole> usrAux = usrFeign.usrsByUser(user)
                    .stream()
                    .map(x->mapper.map(x, UserServerRole.class))
                    .collect(Collectors.toList());
            //Obtenemos lista de servidores por usuario
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
            UserDto user = profileFeign.getUserByUsername(username);
            user.setStatus("desconectado");
            profileFeign.save(user);
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
