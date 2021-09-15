
package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Message;
import com.umeet.umeet.repositories.MessageRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/msg")
@ResponseBody
public class MessagesController {
    
    private MessageRepository repoMsg;
    
    @PostMapping("/channel")
    public List<Message> canales(String id_channel){
        List<Message> aux = repoMsg.findAll();
        
        if (!aux.isEmpty()){
           
        }
        
        return null;
    }
}
