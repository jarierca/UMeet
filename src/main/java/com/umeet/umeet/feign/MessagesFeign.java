package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.MessageChannelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(/*name = "UMeetBack"*/name = "invent", url= "http://localhost:8082")
@RequestMapping("/b/msg")
public interface MessagesFeign {

    @PostMapping("/channel/{id_channel}")
    public List<MessageChannelDto> canales(@PathVariable Long id_channel);
    
    @PostMapping("/private/{id_destino}") 
    public List<MessageChannelDto> privados(@PathVariable Long id_destino, @RequestParam Long idUser);
}
