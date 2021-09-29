
package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "profileInvent",url="http://localhost:8082")
@RequestMapping("/profile")
public interface ProfileFeign {
    
    
    @GetMapping("/view")
    public User view(@RequestParam Long idUser);
    
    @GetMapping("/edit")
    public User edit(@RequestParam Long idUser);
    
    @PostMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void modify(@RequestParam String nickName, @RequestParam String status, @RequestParam String email,  @RequestPart(value = "file")  MultipartFile avatar, @RequestParam Long idUser);
    
    @GetMapping("/remove")
    public void remove(@RequestParam Long idUser);
    
    @PostMapping("/status")
    public void status(@RequestParam Long idUser);
    
    @GetMapping("/statusDrop")
    public void statusDrop(@RequestParam Long idUser);
    
    @GetMapping("/getUser")
    public UserDto getUser(@RequestParam  Long idUser);
    
    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);
}
