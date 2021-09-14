
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
public class Profile {
    
    @Autowired
    ProfileRepository profileRepo;
    
    //Cargar vista con datos del user de la BBDD
    @GetMapping("/edit")
    public String edit(Model m, int id){
        Optional<User> profile = profileRepo.findById(id);
        if(profile.isPresent()){
            m.addAttribute("profile", profile.get());
        }else{
            m.addAttribute("error", "Error, el usuario no existe");
        }
        return "";
    }
    
    //Modifica en la BBDD los datos editados
    @PostMapping("/modify")
    public String modify(Model m, User user){
        profileRepo.save(user);
        return "";
    }
    
    //Borra los datos mediante el id del user
    @GetMapping("/remove")
    public String remove(Model m, int id){
        profileRepo.deleteById(id);
        return "";
    }
    
    //Modificar estado del user
    @PostMapping("/status")
    public String status(){
        return "";
    }
}
