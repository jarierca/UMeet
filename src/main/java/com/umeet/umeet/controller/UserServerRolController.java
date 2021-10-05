package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserServerRolDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Rol;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.repositories.RolRepository;
import com.umeet.umeet.repositories.ServerRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/b/usr")
public class UserServerRolController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UserServerRoleRepository userServerRoleRepository;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/joinServer")
    public Long joinServer(Long idServer, Long idUser){
        User u = userRepository.getById(idUser);
        Rol rol = rolRepository.findById(2l).get();
        Server server = serverRepository.findById(idServer).get();
        User user = userRepository.findById(u.getId()).get();
        UserServerRole relation = new UserServerRole(null, user, rol, server);
        userServerRoleRepository.save(relation);
        return server.getId(); 
    }

    @PostMapping("/leaveServer")
    public void leaveServer(Long idServer,Long idUser){
        User user = userRepository.getById(idUser);
        Server server = serverRepository.findById(idServer).get();
        UserServerRole relation = userServerRoleRepository.findByUserAndServer(user, server).get();
        userServerRoleRepository.deleteById(relation.getId());
    }

    @GetMapping("/usrByUser")
    public List<UserServerRolDto> getUsrByUser(UserDto userDto){
        return userServerRoleRepository.findByUser(mapper.map(userDto, User.class))
                .stream()
                .map(x->mapper.map(x, UserServerRolDto.class))
                .collect(Collectors.toList());
    }
}
