
package com.umeet.umeet.feign;

import com.umeet.umeet.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "profileInvent",url="http://localhost:8082")
@RequestMapping("/profile")
public interface ProfileFeign {
    
    
    @GetMapping("/view")
    public User viewProfile(@RequestParam Long idUser);
    
    @GetMapping("/edit")
    public User editProfile(@RequestParam Long idUser);
    
    @PostMapping("/modify")
    public void modify();
    
}
