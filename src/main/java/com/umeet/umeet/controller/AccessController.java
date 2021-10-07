
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
    private ModelMapper mapper;
    
    @PostMapping("/newregister")
    public Boolean newregister(UserDto userDto){
        Optional<User> usuarios = userRepository.findByUsername(userDto.getUsername());
        if (!usuarios.isPresent()) {

            userDto.setNickName(userDto.getUsername());
            userDto.setAvatar("C:/zzUpload/avatar/avatar-stock.png");
            userDto.setStatus("desconectado");
            userDto.setOauth2("user");

            User user = mapper.map(userDto, User.class);
            userRepository.save(user);

            return true;
        }else{
            return false;
        }
    }
    
    @PostMapping("/userExist")
    public Boolean userExist(String username, String oauth){
        Optional<User> usuarios = userRepository.findByUsernameAndOauth2(username,oauth);
        if (usuarios.isPresent()) {
            return true;
        }else{
            return false;
        }
    }
    
    //No le llega el objeto
    @PostMapping("/newregisterOAuth")
    public void newregisterOAuth(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
    }
}
