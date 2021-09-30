package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.dtos.ViewServerDto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerFeign serverFeign;

    @GetMapping("/allServers")
    public String allServers(Model m) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ServerDto> allServers = serverFeign.allServers(u.getId());
        m.addAttribute("nam", allServers);

        return "/servers/allServers";
    }

    @ResponseBody
    @PostMapping("/byUser")
    public List<ServerDto> serverByUserJson(Model m) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ServerDto> servers = serverFeign.getServersByUser(u.getId());
        return servers;
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
        serverDto = serverFeign.addServer(u.getId(), serverDto, file);
        return "redirect:/server/one?idServer="+serverDto.getId();
    }

    @GetMapping("/deleteServer")
    public String deleteServer(Long idServer) {
        serverFeign.deleteServer(idServer);
        return "redirect:/home";
    }

    @GetMapping("/one")
    public String viewServer(Model model, Long idServer){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ViewServerDto viewServerDto = serverFeign.getViewServerDto(u.getId(), idServer);

        model.addAttribute("server", viewServerDto.getServer());
        model.addAttribute("userServer", viewServerDto.getUserServerRoles());
        model.addAttribute("usr", viewServerDto.getUsr());
        model.addAttribute("categories", viewServerDto.getCategories());
        model.addAttribute("idServer", idServer);
        return "servers/viewServer";
    }
}