
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.ProfileRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Value("${carpetas.recursos.umeet}")
    private String rutaRecursos; 
    
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
    public String modify(Model m, String nickName, String email, MultipartFile avatar, long id){
        Optional<User> user = profileRepo.findById(id);
        user.get().setNickName(nickName);
        user.get().setEmail(email);
        
        String ruta = rutaRecursos + "\\avatar\\users\\" + user.get().getUsername()+".png";
        File f = new File(ruta);
        f.getParentFile().mkdirs();
        try{
            Files.copy(avatar.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);    
        }catch(IOException e){
            e.printStackTrace();
            m.addAttribute("error", "Error inesperado");
        }    
        
        user.get().setAvatar(ruta);
        
        profileRepo.save(user.get());
        return "redirect:view?id="+id;
    }   
    
    @GetMapping("/avatar")
    public ResponseEntity<Resource> avatar(String url){
        
        HttpHeaders cabeceras=new HttpHeaders();
        cabeceras.add("Content-Disposition", "attachment;");
        cabeceras.add("Cache-Control", "no-cache, no-store, must-revalidate");
        cabeceras.add("Pragma", "no-cache");
        cabeceras.add("Expires", "0");
        
        try{
            return ResponseEntity.ok()
                                 .headers(cabeceras)
                                 .contentLength((new File(url)).length())
                                 .contentType(MediaType.parseMediaType( "application/octet-stream" ))
                                 .body(new InputStreamResource(new FileInputStream( url )) );
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
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
