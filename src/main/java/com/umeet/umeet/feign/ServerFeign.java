package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.dtos.ViewServerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@FeignClient(contextId = "feign2", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/server")
public interface ServerFeign {

    @GetMapping("/allServers")
    public List<ServerDto> allServers(@RequestParam Long idUser);

    @PostMapping("/filtered")
    public List<ServerDto> filteredServers(@RequestParam String name, @RequestParam Long idUser);

    @GetMapping("/{idServer}")
    public ServerDto viewServer(@PathVariable Long idServer);

    @PostMapping(value="/addServer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServerDto addServer(@RequestParam Long idUser, @SpringQueryMap ServerDto serverDto, @RequestPart(value = "file") MultipartFile file);

    @PostMapping("/byUser")
    public List<ServerDto> getServersByUser(@RequestParam Long idUser);

    @DeleteMapping("/{idServer}")
    public void deleteServer(@PathVariable Long idServer);

    @GetMapping("/one")
    public ViewServerDto getViewServerDto(@RequestParam Long idUser, @RequestParam Long idServer);
}
