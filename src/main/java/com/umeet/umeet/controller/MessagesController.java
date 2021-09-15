
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.MessageChannelDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.repositories.ChannelRepository;
import com.umeet.umeet.repositories.MessageRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/msg")
@ResponseBody
public class MessagesController {
    
    @Autowired
    private MessageRepository repoMsg;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChannelRepository repoChn;
    
    @PostMapping("/channel/{id_channel}") //Devuelve un Json con toda la información de un canal
    public List<MessageChannelDto> canales(@PathVariable Long id_channel){
        Optional<Channel> aux = repoChn.findById(id_channel);
        if (aux.isPresent()){
            List<MessageChannelDto> res = aux.get().getMessages()
                                        .stream()
                                        .map(x->mapper.map(x, MessageChannelDto.class))
                                        .collect(Collectors.toList());
           
            return res;
        }
        return null;
    }
    
    @PostMapping("/private/{id_user}") //Devuelve un Json con los mensajes privados de 1 usuario origen
    public List<MessageChannelDto> privados(@PathVariable Long id_user){
        List<Message> aux = repoMsg.findAll();
        if (!aux.isEmpty()){
            aux = aux.stream()
                      .filter(x->x.getUser().getId()==id_user && x.getUserDestiny()!=null)
                      .collect(Collectors.toList());
            List<MessageChannelDto> res = aux.stream()
                                        .map(x->mapper.map(x, MessageChannelDto.class))
                                        .collect(Collectors.toList());
           
            return res;
        }
        return null;
    }
    
    @PostMapping("/channel/sendmsg")
    public void mensajeCanal(Message msg){
        
        
        //repoMsg.save(msg);
    }
}
