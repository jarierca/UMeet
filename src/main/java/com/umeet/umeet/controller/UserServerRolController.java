package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Rol;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.repositories.RolRepository;
import com.umeet.umeet.repositories.ServerRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/usr")
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
    public String joinServer(Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Rol rol = rolRepository.findById(2l).get();
        Server server = serverRepository.findById(idServer).get();
        User user = userRepository.findById(u.getId()).get();
        UserServerRole relation = new UserServerRole(null, user, rol, server);
        userServerRoleRepository.save(relation);
        return "redirect:server/one?idServer=" + server.getId();
    }

    @GetMapping("/leaveServer")
    public String leaveServer(Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = userRepository.findById(u.getId()).get();
        Server server = serverRepository.findById(idServer).get();
        UserServerRole relation = userServerRoleRepository.findByUserAndServer(user, server).get();
        userServerRoleRepository.deleteById(relation.getId());
        return "redirect:home";
    }
}
