package com.umeet.umeet.interfaces;

import com.umeet.umeet.entities.Server;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IServerService{
    public void deleteServerCascade(Long idServer);
    public List<Server> filterServers(List<Server> serversList);
}
