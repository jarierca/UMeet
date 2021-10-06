
package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(contextId = "profileFeign", name = "UMeetBack")
@RequestMapping("/b/profile")
public interface ProfileFeign {
    
    
    @GetMapping("/view")
    public UserDto view(@RequestParam Long idUser);
    
    @GetMapping("/edit")
    public UserDto edit(@RequestParam Long idUser);
    
    @PostMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void modify(@RequestParam String nickName, @RequestParam String status, @RequestParam String email,  @RequestPart(value = "file")  MultipartFile file, @RequestParam Long idUser);
    
    @GetMapping("/remove")
    public void remove(@RequestParam Long idUser);
    
    @PostMapping("/status")
    public void status(@RequestParam String status, @RequestParam Long idUser);
    
    @GetMapping("/statusDrop")
    public void statusDrop(@RequestParam String status, @RequestParam Long idUser);
    
    @GetMapping("/getUser")
    public UserDto getUser(@RequestParam  Long idUser);

    @GetMapping("/getUserByUsername")
    public UserDto getUserByUsername(@RequestParam String username);

    @PostMapping("/save")
    public void save(@SpringQueryMap UserDto userDto);
}
