/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.umeet.umeet.repositories.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/servers")
public class ServerUserController {

    @Autowired
    private ServerRepository repoServer;
    @Autowired
    private UserRepository repoUser;
    
    @GetMapping
    public String allServers(Model m) {

        m.addAttribute("nam", repoServer.findAll());

        return "/servers/allServers";
    }

    @GetMapping("/byUser")
    public String serverByUser(Model m, Long userId) {

        m.addAttribute("user", repoUser.findById(userId).get());

        return "/servers/byUser";
    }

    

    @PostMapping("/filtered")
    public String searchServer(Model m, String name) {

        List<Server> aux = repoServer.findByNameContaining(name);
        if (!aux.isEmpty()) {
            m.addAttribute("nam", aux);
        }
        List<Server> aux1 = repoServer.findByDescriptionContaining(name);
        if (!aux1.isEmpty()) {
            m.addAttribute("des", aux1);
        }

        
        return "servers/filteredServers";

    }
    
    
    @PostMapping("/addServer")
    
    public String addServer (Model model, Server server){
        
        
        repoServer.save(server);
        //terminar
        
        return "/servers/allServers";
               
    }
   
    
    

}
