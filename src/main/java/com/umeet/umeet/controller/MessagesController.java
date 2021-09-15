
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.MessageChannelDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.repositories.ChannelRepository;
import com.umeet.umeet.repositories.MessageRepository;
import com.umeet.umeet.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/msg")
//@ResponseBody
public class MessagesController {
    
    @Autowired
    private MessageRepository repoMsg;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChannelRepository repoChn;
    
    @Autowired
    private UserRepository repoUsr;
    
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
    
    @GetMapping
    public String inicio(Model m){
        m.addAttribute("message",new Message());
        return "/messages/vista";
    }
    
    @PostMapping("/channel/sendmsg") //Guarda mensajes en un canal por un usuario
    public void mensajeCanal(Message msg,Long idChannel,Long idUser){
        msg.setChannel(repoChn.findById(idChannel).get());
        msg.setUser(repoUsr.findById(idUser).get());
        repoMsg.save(msg);
    }
    
    @PostMapping("/private/sendmsg") //Guarda mensajes privados entre usuarios
    public void mensajePrivado(Message msg,Long idUser,Long idUserDestiny){
        msg.setUser(repoUsr.findById(idUser).get());
        msg.setUserDestiny(repoUsr.findById(idUserDestiny).get());
        repoMsg.save(msg);
    }
}
