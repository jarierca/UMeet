
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.feign.EmailFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class EmailController {
    
    @Autowired
    private EmailFeign mailFeign;
    
    @PostMapping("/mail")
    public void mensajeFileCanal(String email, String subject, String text){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        mailFeign.mail(email, subject, text);
    }   
    
    @PostMapping("/mailAttach")
    public void mensajeFilePrivado(String email, String subject, String text, String file){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        mailFeign.mailAttach(email, subject, text, file);
    }
}
