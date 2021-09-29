package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.CategoryViewDto;
import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.feign.ServerFeign;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/server")
public class ServerController {

    @Value("${carpetas.recursos.umeet}")
    private String rutaRecursos;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private IServerService serverService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserServerRoleRepository userServerRoleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServerFeign serverFeign;

//    @PostMapping("/allServers")
    @GetMapping("/allServers")
    public String allServers(Model m) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ServerDto> allServers = serverFeign.allServers(u.getId());
        m.addAttribute("nam", allServers);

        return "/servers/allServers";
    }

    @GetMapping("/byUser")
    public String serverByUser(Model m) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //m.addAttribute("user", userRepository.findById(userId).get());
        User user = userRepository.findById(u.getId()).get();
        List<UserServerRole> aux = userServerRoleRepository.findByUser(user);
        if (!aux.isEmpty()){
            List<Server> usr = userServerRoleRepository.findByUser(user).stream().map(x->x.getServer()).collect(Collectors.toList());
            m.addAttribute("server", usr);
        }else{
            m.addAttribute("server", new Server());
        }
        return "servers/byUser";
        
    }
    
    @ResponseBody
    @PostMapping("/byUser")
    public List<ServerDto> serverByUserJson(Model m) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //m.addAttribute("user", userRepository.findById(userId).get());
        User user = userRepository.findById(u.getId()).get();
        List<UserServerRole> aux = userServerRoleRepository.findByUser(user);
        if (!aux.isEmpty()){
            List<ServerDto> usr = userServerRoleRepository.findByUser(user).stream().map(x->mapper.map(x.getServer(),ServerDto.class)).collect(Collectors.toList());
            return usr;
        }else{
            return null;
        }
        
    }

    @PostMapping("/filtered")
    public String searchServer(Model m, String name, Long idUser) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        m.addAttribute("nam", serverFeign.filteredServers(name, u.getId()));

        return "/servers/filteredServers";
    }

    @GetMapping("/form")
    public String viewServerCreation(Model model, Long idServer) {
        if (idServer == null) {
            model.addAttribute("server", new ServerDto());
        } else {
            model.addAttribute("server", serverFeign.viewServer(idServer));
        }
        return "/servers/formServer";
    }

    @PostMapping("/addServer")
    public String addServer(ServerDto serverDto, MultipartFile file) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Map<String, Object> data = new HashMap<>();
        data.put("serverDto", serverDto);
        data.put("file", file);
        data.put("idUser", u.getId());
        //serverFeign.addServer(data);
        return "redirect:/server/one?idServer="+serverDto.getId();
    }

    @GetMapping("/deleteServer")
    public String deleteServer(Long idServer) {
        serverRepository.deleteById(idServer);
        return "redirect:server/byUser";
    }

    @GetMapping("/one")
    public String viewServer(Model model, Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Server server = serverRepository.findById(idServer).get();
        User user = userRepository.findById(u.getId()).get();
        List<CategoryViewDto> categories = categoryRepository.findByServer(server).stream()
                        .map(x->mapper.map(x, CategoryViewDto.class))
                        .collect(Collectors.toList());
        UserServerRole usr = userServerRoleRepository.findByUserAndServer(user, server).get();
        
        List<UserServerRole> userServer = userServerRoleRepository.findByServer(serverRepository.findById(idServer).get());
        
        model.addAttribute("server", server);
        model.addAttribute("userServer", userServer);
        model.addAttribute("usr", usr);
        model.addAttribute("categories", categories);
        model.addAttribute("idServer", idServer);
        return "servers/viewServer";
    }
}