package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Rol;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.feign.UserServerRoleFeign;
import com.umeet.umeet.repositories.RolRepository;
import com.umeet.umeet.repositories.ServerRepository;
import com.umeet.umeet.repositories.UserRepository;
import com.umeet.umeet.repositories.UserServerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
    @Autowired
    UserServerRoleFeign usrFeign;

    @GetMapping("/joinServer")
    public String joinServer(Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Server server = usrFeign.joinServer(idServer, u.getId());
        return "redirect:/server/one?idServer=" + server.getId();
    }

    @GetMapping("/leaveServer")
    public String leaveServer(Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        usrFeign.leaveServer(idServer, u.getId());
        return "redirect:/home";
    }
}
