
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.ProfileRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.services.FriendService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/b/profile")
public class ProfileController {
    
    @Value("${carpetas.recursos.umeet}")
    private String rutaRecursos; 
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    ProfileRepository profileRepository;
    
    @Autowired
    FriendService friendService;

    @Autowired
    UserRepository userRepository;
    
    //Visualizar los datos del user
    @GetMapping("/view")
    public UserDto view(Long idUser){
        
        Optional<User> profile = profileRepository.findById(idUser);
        UserDto dto = mapper.map(profile.get(), UserDto.class);
        
        return dto;
    }
    
    //Cargar vista con datos del user de la BBDD
    @GetMapping("/edit")
    public UserDto edit(Long idUser){
        
        Optional<User> profile = profileRepository.findById(idUser);
        UserDto dto = mapper.map(profile.get(), UserDto.class);
        return dto;
    }
    
    //Modifica en la BBDD los datos editados
    @PostMapping("/modify")
    public void modify(String nickName,String status, String email, MultipartFile file , Long idUser){
        
        Optional<User> user = profileRepository.findById(idUser);
        user.get().setNickName(nickName);
        user.get().setEmail(email);
        user.get().setStatus(status);
        

        if (!file.isEmpty()) {

            String ruta = rutaRecursos + "/avatar/users/" + user.get().getUsername() + ".png";
            File f = new File(ruta);
            f.getParentFile().mkdirs();
            try {
                Files.copy(file.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            user.get().setAvatar(ruta);
        }
        
        profileRepository.save(user.get());
    } 
    
    //Borra los datos mediante el id del user
    @GetMapping("/remove")
    public void remove(Model m, Long idUser){
        friendService.deleteFriendCascade(idUser);
    }
    
    //Modificar estado del user
    @PostMapping("/status")
    public void status(String status, Long idUser){
        Optional<User> user = profileRepository.findById(idUser);
        user.get().setStatus(status);
        profileRepository.save(user.get());
    }
    
    @GetMapping("/statusDrop")
    public void statusDrop(String status, Long idUser){
        Optional<User> user = profileRepository.findById(idUser);
        user.get().setStatus(status);
        profileRepository.save(user.get());
    }
    
    
    //Obtiene los datos del user
    @GetMapping("/getUser")
    public UserDto getUser(Model m, Long idUser){
        Optional<User> user = profileRepository.findById(idUser);
        UserDto userDto = mapper.map(user.get(), UserDto.class);
        
        //m.addAttribute("user",user.get());
        return userDto;
    }    
    
    @GetMapping("/getUsername")
    public UserDto getUsername(Model m, String username){
        Optional<User> user = profileRepository.findByUsername(username);
        UserDto userDto = mapper.map(user.get(), UserDto.class);
        
        //m.addAttribute("user",user.get());
        return userDto;
    }  
    
    @GetMapping("/recoverPass")
    public UserDto recoverPass(Model m, String username, String codigo){
        Optional<User> user = profileRepository.findByUsername(username);
        UserDto userDto = mapper.map(user.get(), UserDto.class);
        
        if(userDto.getCodigo().equals(codigo)){
            return userDto;
        }else{
            return null;
        }
        //m.addAttribute("user",user.get());
        
    }  
    
    @GetMapping("/newCode")
    public void newCode(Model m, Long idUser, String codigo){
        Optional<User> user = profileRepository.findById(idUser);
        user.get().setCodigo(codigo);
        profileRepository.save(user.get());
        
        UserDto userDto = mapper.map(user.get(), UserDto.class);
        
//        return userDto;
    }  
    
    @PostMapping("/modifyPass")
    public void modifyPass(String password, Long idUser){
        
        Optional<User> user = profileRepository.findById(idUser);
        user.get().setPass(password);
        
        profileRepository.save(user.get());
    } 


    @GetMapping("/getUserByUsername")
    public UserDto getUserByUserName(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return mapper.map(user.get(), UserDto.class);
        } else {
            return new UserDto();
        }
    }

    @PostMapping("/save")
    public void save(UserDto userDto){
        userRepository.save(mapper.map(userDto, User.class));
    }
}
