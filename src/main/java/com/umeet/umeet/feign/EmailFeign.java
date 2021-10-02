package com.umeet.umeet.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "feignEmail", name = "UMeetEmail", url= "http://localhost:8083")
@RequestMapping("/m")
public interface EmailFeign {
    
    @GetMapping("/mail") 
    public void mail(@RequestParam String email, @RequestParam String subject, @RequestParam String text);
    
    @GetMapping("/mailAttach")
    public void mailAttach(@RequestParam String email, @RequestParam String subject, @RequestParam String text, @RequestParam String file);
    
}
