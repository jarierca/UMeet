package com.umeet.umeet.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "feignEmail", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/m")
public interface EmailFeign {
    
    @PostMapping("/mail") 
    public void mail(@RequestParam String email, @RequestParam String subject, @RequestParam String text);
    
    @PostMapping("/mailAttach")
    public void mailAttach(@RequestParam String email, @RequestParam String subject, @RequestParam String text, @RequestParam String file);
    
}
