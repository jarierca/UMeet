
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.feign.EmailFeign;
import com.umeet.umeet.feign.ProfileFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Value("${ruta.enlace.umeet}")
    private String enlace;
    
    @Autowired
    private EmailFeign emailFeign;
    
    @Autowired
    private ProfileFeign profileFeign;
    
    //Visualizar los datos del user
    @GetMapping("/view")
    public String view(Model m){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        UserDto profile = profileFeign.edit(u.getId());
        
        m.addAttribute("profile", profile);
        
        return "profile/view";
    }
    
    //Cargar vista con datos del user de la BBDD
    @GetMapping("/edit")
    public String edit(Model m){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        UserDto profile = profileFeign.edit(u.getId());
        m.addAttribute("profile", profile);
        
        return "editProfile";
    }
    
    //Modifica en la BBDD los datos editados
    @PostMapping("/modify")
    public String modify(Model m, String nickName,String status, String email, MultipartFile avatar){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        profileFeign.modify(nickName, status, email, avatar, u.getId());
        
        return "redirect:view";
    }
    
    //Obtiene la imagen del avatar del user
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
    public String remove(Model m){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        profileFeign.remove(u.getId());
        return "redirect:/logout";
    }
    
    //Modificar estado del user
    @PostMapping("/status")
    public String status(String status){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        profileFeign.status(status, u.getId());
        return "redirect:view";
    }
    
    @ResponseBody
    @GetMapping("/statusDrop")
    public void statusDrop(String status){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        profileFeign.statusDrop(status, u.getId());
    }
    
    //Obtiene los datos del user
    @GetMapping("/getUser")
    @ResponseBody
    public UserDto getUser(Model m){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
         
        UserDto userDto = profileFeign.getUser(u.getId());
        
        return userDto;
    }     
    
    @GetMapping("/sendEmail")
    public String sendEmail(Model m){
        return "profile/setUsername";
    }
    
    @PostMapping("/resetPassView")
    public String resetPassView(Model m, String username){
        
        UserDto user = profileFeign.getUsername(username);
        if(user != null){
            m.addAttribute("username",username);
            return "profile/resetPass";
        }else{
            return "profile/setUsername";
        }  
    }
    
    @PostMapping("/modifyPass")
    public String modifyPass(Model m, String pass, String username){
        UserDto user = profileFeign.getUsername(username);
        
        BCryptPasswordEncoder cifrar = new BCryptPasswordEncoder();
        pass = cifrar.encode(pass);
        
        profileFeign.modifyPass(pass, user.getId());
        
        return "redirect:/login";
    }
    
    @PostMapping("/newCode")
    public String newCode(Model m, String username){
        UserDto user = profileFeign.getUsername(username);
        
        String codigo = UUID.randomUUID().toString();
        
        profileFeign.newCode(user.getId(),codigo);
        
        String txt = "Hola " + user.getUsername() + ", has iniciado el proceso de recuperacion de tu contraseña para ello puedos hacerlo con el siguiente enlace "+ enlace+"/"+codigo+"/"+username;
        emailFeign.mail(user.getEmail(), "Recuperar Contraseña", txt);
        
        return "redirect:/login";
    }
    
    @GetMapping("/recoverPass/{codigo}/{username}")
    public String recoverPass(Model m,@PathVariable String codigo,@PathVariable String username){
        
        UserDto userDto = profileFeign.recoverPass(username,codigo);
        
        if(userDto != null){
            m.addAttribute("username",username);
            return "/profile/resetPass";
        }else{
            return null;
        }
    }
}
