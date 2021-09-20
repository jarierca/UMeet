
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.services.CookieService;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessController {
    
    @Autowired
    private CookieService CookieService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String login(){ 
        return "/login";
    }
    
    @GetMapping("/register")
    public String register(Model m){
        m.addAttribute("user", new User());
        return "register";
    }
    
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
    public String newregister(Model m, User user){
        Optional<User> usuarios = userRepository.findByUsername(user.getUsername());
        if (!usuarios.isPresent()) {

            String encodedPassword = passwordEncoder.encode(user.getPass());
            user.setPass(encodedPassword);

            user.setNickName(user.getUsername());
            user.setAvatar("C:/zzUpload/avatar/avatar-stock.png");
            user.setStatus("desconectado");

            userRepository.save(user);
            
            return "login";
        }else{
            m.addAttribute("error","El usuario que has introducido no esta disponible");
            return "register";
        }
    }
    
    @GetMapping("/home")
    public String index(HttpServletResponse response , Model m , @CookieValue(name = "idUser",required = false) Long cookieIdUser){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null){
            if(cookieIdUser == null){
                String username = auth.getName();
           
                Optional<User> user = userRepository.findByUsername(username);

                CookieService.setCookieUser(response, user.get(), 30 * 24 * 60 * 60);

                m.addAttribute("user",user.get());
            }
        }
        
        return "index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null){
            String username = auth.getName();
           
            Optional<User> user = userRepository.findByUsername(username);

            CookieService.setCookieUser(response, user.get(), 0 * 24 * 60 * 60);
        } 
        
        return "index";
    }
}
