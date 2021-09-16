package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/server")
public class ServerController {

    @Value("${carpetas.recursos.umeet}")
    private String rutaRecursos;

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

    @GetMapping("/allServers")
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

        return "/servers/filteredServers";
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
    public String addServer(Server server, Long idUser, MultipartFile file) {
        if(!file.isEmpty()){
            String ruta = rutaRecursos + "\\avatar\\users\\" + server.getName() + ".png";
            File f = new File(ruta);
            f.getParentFile().mkdirs();
            try{
                Files.copy(file.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                e.printStackTrace();
            }
            server.setAvatar(ruta);
        }
        serverRepository.save(server);
        List<UserServerRole> userServerRoles = userServerRoleRepository.findByServer(server);
        if(userServerRoles.isEmpty()){
            UserServerRole userServerRole = new UserServerRole();
            userServerRole.setUser(userRepository.findById(idUser).get());
            userServerRole.setRol(rolRepository.findById(1l).get());
            userServerRole.setServer(server);
            userServerRoleRepository.save(userServerRole);
        }
        return "redirect:/server/byUser?userId="+idUser;

    }

    @GetMapping("/deleteServer")
    public String deleteServer(Long idServer) {
        serverService.deleteServerCascade(idServer);
        return "/redirect:server/byUser";
    }
}
