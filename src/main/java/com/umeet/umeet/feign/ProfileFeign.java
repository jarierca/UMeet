
package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profileInvent",url="http://localhost:8082")
@RequestMapping("/profile")
public interface ProfileFeign {
    
    
    @GetMapping("/view")
    public User view(@RequestParam Long idUser);
    
    @GetMapping("/edit")
    public User edit(@RequestParam Long idUser);
    
    @PostMapping("/modify")
    public void modify();
    
    @GetMapping("/remove")
    public void remove(@RequestParam Long idUser);
    
    @PostMapping("/status")
    public void status(@RequestParam Long idUser);
    
    @GetMapping("/statusDrop")
    public void statusDrop(@RequestParam Long idUser);
    
    @GetMapping("/getUser")
    public UserDto getUser(@RequestParam  Long idUser);
}
