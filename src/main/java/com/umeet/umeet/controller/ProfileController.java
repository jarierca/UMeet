
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.ProfileRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    ProfileRepository profileRepo;
    
    @GetMapping("/view")
    public String view(Model m, long id){
        Optional<User> profile = profileRepo.findById(id);
        if(profile.isPresent()){
            m.addAttribute("profile", profile.get());
        }else{
            m.addAttribute("error", "Error, el usuario no existe");
        }
        return "/profile/view";
    }
    
    //Cargar vista con datos del user de la BBDD
    @GetMapping("/edit")
    public String edit(Model m, long id){
        Optional<User> profile = profileRepo.findById(id);
        if(profile.isPresent()){
            m.addAttribute("profile", profile.get());
        }else{
            m.addAttribute("error", "Error, el usuario no existe");
        }
        return "/profile/modify";
    }
    
    //Modifica en la BBDD los datos editados
    @PostMapping("/modify")
    public String modify(Model m, User user, long id){
        profileRepo.save(user);
        return "redirect:view";
    }
    
    //Borra los datos mediante el id del user
    @GetMapping("/remove")
    public String remove(Model m, long id){
        profileRepo.deleteById(id);
        return "redirect:view";
    }
    
    //Modificar estado del user
    @PostMapping("/status")
    public String status(){
        return "";
    }
}
