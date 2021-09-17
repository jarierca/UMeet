package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Rol;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.repositories.RolRepository;
import com.umeet.umeet.repositories.ServerRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/usr")
public class UserServerRolController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UserServerRoleRepository userServerRoleRepository;

    @GetMapping("/joinServer")
    public String joinServer(Long idServer, Long idUser){
        Rol rol = rolRepository.findById(2l).get();
        Server server = serverRepository.findById(idServer).get();
        User user = userRepository.findById(idUser).get();
        UserServerRole relation = new UserServerRole(null, user, rol, server);
        userServerRoleRepository.save(relation);
        return "";
    }
}
