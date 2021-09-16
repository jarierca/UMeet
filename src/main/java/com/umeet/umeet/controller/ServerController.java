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

    @GetMapping
    public String allServers(Model m) {

        m.addAttribute("nam", serverRepository.findAll());

        return "/servers/allServers";
    }

    @GetMapping("/byUser")
    public String serverByUser(Model m, Long userId) {

        m.addAttribute("user", userRepository.findById(userId).get());

        return "/servers/byUser";
    }

    @PostMapping("/filtered")
    public String searchServer(Model m, String name) {

        List<Server> aux = serverRepository.findByNameContaining(name);
        if (!aux.isEmpty()) {
            m.addAttribute("nam", aux);
        }
        List<Server> aux1 = serverRepository.findByDescriptionContaining(name);
        if (!aux1.isEmpty()) {
            m.addAttribute("des", aux1);
        }

        return "servers/filteredServers";
    }

   /* @GetMapping("/pruebaServer")
    public String prueba(Model model) {
        model.addAttribute("servers", serverRepository.findAll());
        return "prueba";
    }
*/
    @GetMapping("/form")
    public String viewServerCreation(Model model, Long idServer, Long idUser) {
        if (idServer == null) {
            model.addAttribute("server", new Server());
        } else {
            model.addAttribute("server", serverRepository.findById(idServer));
        }
        model.addAttribute("idUser", idUser);
        return "/servers/formServer";
    }
    

    @PostMapping("/addServer")
    public String addServer(Server server, Long idUser) {
        serverRepository.save(server);
        UserServerRole userServerRole = new UserServerRole();
        userServerRole.setUser(userRepository.findById(idUser).get());
        userServerRole.setRol(rolRepository.findById(1l).get());
        userServerRole.setServer(server);
        userServerRoleRepository.save(userServerRole);
        return "redirect:server/byUser";

    }

    @GetMapping("/deleteServer")
    public String deleteServer(Long idServer) {
        serverService.deleteServerCascade(idServer);
        return "redirect:server/byUser";
    }
}
