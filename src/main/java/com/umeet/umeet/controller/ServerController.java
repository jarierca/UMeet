package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.CategoryViewDto;
import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//    @PostMapping("/allServers")
    @GetMapping("/allServers2")
    public Map<String, Object> allServers2( Long idUsuario) {
        Map<String,Object> m=new HashMap<>();
        List<Server> allServers = serverRepository.findAll();
        allServers = serverService.filterServers(allServers, idUsuario);
        List<ServerDto> servers = allServers.stream()
                .map(x->mapper.map(x, ServerDto.class))
                .collect(Collectors.toList());
        m.put("dato1", servers);
        m.put("dato2", 3);
        return m;
    }

    @GetMapping("/allServers")
    public List<ServerDto> allServers(Long idUser) {
        List<Server> allServers = serverRepository.findAll();
        allServers = serverService.filterServers(allServers, idUser);
        List<ServerDto> servers = allServers.stream()
                .map(x->mapper.map(x, ServerDto.class))
                .collect(Collectors.toList());
        return servers;
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
    public List<ServerDto> searchServer(String name, Long idUser) {

        List<Server> aux = serverRepository.findByNameContaining(name);
       
        List<Server> aux1 = serverRepository.findByDescriptionContaining(name);
        
        List<Server> aux2 = Stream.concat(aux.stream(), aux1.stream())
                .distinct()
                .collect(Collectors.toList());
        aux2 = serverService.filterServers(aux2, idUser);
        List<ServerDto> servers = aux2.stream()
                .map(x->mapper.map(x, ServerDto.class))
                .collect(Collectors.toList());

        return servers;
    }

    @GetMapping("/form")
    public String viewServerCreation(Model model, Long idServer) {
        if (idServer == null) {
            model.addAttribute("server", new Server());
        } else {
            model.addAttribute("server", serverRepository.findById(idServer));
        }
        return "servers/formServer";
    }

    @GetMapping("/{idServer}")
    public ServerDto viewServer(@PathVariable Long idServer){
        Server server = serverRepository.findById(idServer).get();
        ServerDto serverDto = mapper.map(server, ServerDto.class);
        return serverDto;
    }

    @PostMapping("/addServer")
    public String addServer(Map<String, Object> data) {
        Server server = mapper.map(data.get("serverDto"), Server.class);
        Long idUser = (Long) data.get("idUser");
        MultipartFile file = (MultipartFile) data.get("file");
        String avatarServer = "";
        if(server.getId()==null){
            avatarServer = rutaRecursos+"/avatar/server-stock.png";
        } else {
            avatarServer = serverRepository.findById(server.getId()).get().getAvatar();
        }
        if(file.isEmpty()){
            server.setAvatar(avatarServer);
        } else {
            String ruta = rutaRecursos + "/avatar/servers/" + server.getName() + ".png";
            ruta = ruta.replace(" ", "-");
            File f = new File(ruta);
            f.getParentFile().mkdirs();
            try{
                Files.copy(file.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                e.printStackTrace();
            }
            server.setAvatar(ruta);
        }
        server = serverRepository.save(server);
        List<UserServerRole> userServerRoles = userServerRoleRepository.findByServer(server);
        if(userServerRoles.isEmpty()){
            UserServerRole userServerRole = new UserServerRole();
            userServerRole.setUser(userRepository.findById(idUser).get());
            userServerRole.setRol(rolRepository.findById(1l).get());
            userServerRole.setServer(server);
            userServerRoleRepository.save(userServerRole);
        }
        return "redirect:/server/one?idServer="+server.getId();
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