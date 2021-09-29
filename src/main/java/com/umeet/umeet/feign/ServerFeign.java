package com.umeet.umeet.feign;

import com.umeet.umeet.configuration.FeignConfig;
import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.entities.Server;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@FeignClient(contextId = "feign2", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/servers")
public interface ServerFeign {

    @GetMapping("/allServers")
    public List<ServerDto> allServers(@RequestParam Long idUser);

    @PostMapping("/filtered")
    public List<ServerDto> filteredServers(@RequestParam String name, @RequestParam Long idUser);

    @GetMapping("/{idServer}")
    public ServerDto viewServer(@PathVariable Long idServer);

    @PostMapping(value="/addServer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServerDto addServer(@RequestParam Long idUser, @SpringQueryMap ServerDto serverDto, @RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);

}
