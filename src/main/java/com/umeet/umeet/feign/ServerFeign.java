package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.entities.Server;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "UMeetBack")
@RequestMapping("/servers")
public interface ServerFeign {

    @GetMapping("/allServers")
    public List<ServerDto> allServers(@RequestParam Long idUser);

    @PostMapping("/filtered")
    public List<ServerDto> filteredServers(@RequestParam String name, @RequestParam Long idUser);

    @GetMapping("/{idServer}")
    public ServerDto viewServer(@PathVariable Long idServer);

    @PostMapping("/addServer")
    public void addServer(@SpringQueryMap Map<String, Object> data);
}
