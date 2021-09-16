
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccessController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/login")
    public String login(){ 
        return "/login";
    }
    
    @GetMapping("/register")
    public String register(Model m){
        m.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/newregister")
    public String newregister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPass());
        user.setPass(encodedPassword);
        
        user.setNickName(user.getUsername());
        user.setAvatar("C:\\zzUpload\\avatar\\users\\avatar-stock.png");
        user.setStatus("desconectado");
        
        userRepository.save(user);
        return "login";
    }
}
