package com.umeet.umeet.services;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServerService implements IServerService {

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFieldRepository;

    @Autowired
    UserServerRoleRepository userServerRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Server> filterServers(List<Server> serversList, Long idUser) {
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = userRepository.findById(u.getId()).get();
        List<Server> servers = userServerRoleRepository.findByUser(user).stream()
                .map(x->x.getServer())
                .collect(Collectors.toList());
        serversList = serversList.stream()
                .filter(x->!servers.contains(x))
                .collect(Collectors.toList());
        return serversList;
    }
}
