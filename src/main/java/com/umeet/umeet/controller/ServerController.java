package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/server")
public class ServerController {

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    IServerService serverService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UserServerRoleRepository userServerRoleRepository;

    @GetMapping("/pruebaServer")
    public String prueba(Model model){
        model.addAttribute("servers", serverRepository.findAll());
        return "prueba";
    }

    @GetMapping("/form")
    public String viewServerCreation(Model model, Long idServer, Long idUser){
        if(idServer==null){
            model.addAttribute("server", new Server());
        } else {
            model.addAttribute("server", serverRepository.findById(idServer));
        }
        model.addAttribute("idUser", idUser);
        return "formServer";
    }

    @PostMapping("/addServer")
    public String addServer(Server server, Long idUser){
        serverRepository.save(server);
        UserServerRole userServerRole = new UserServerRole();
        userServerRole.setUser(userRepository.findById(idUser).get());
        userServerRole.setRol(rolRepository.findById(1l).get());
        userServerRole.setServer(server);
        userServerRoleRepository.save(userServerRole);
        return "redirect:pruebaServer";
    }

    @GetMapping("/deleteServer")
    public String deleteServer(Long idServer){
        serverService.deleteServerCascade(idServer);
        return "redirect:pruebaServer";
    }
}
