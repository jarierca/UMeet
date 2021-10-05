package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserServerRolDto;
import com.umeet.umeet.entities.Server;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(contextId = "feignUsr", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/usr")
public interface UserServerRoleFeign {
    
    @PostMapping("/joinServer")
    public Long joinServer(@RequestParam Long idServer, @RequestParam Long idUser);
    
    @PostMapping("/leaveServer")
    public void leaveServer(@RequestParam Long idServer, @RequestParam Long idUser);

    @GetMapping("/usrByUser")
    public List<UserServerRolDto> usrsByUser(@SpringQueryMap UserDto userDto);
}
