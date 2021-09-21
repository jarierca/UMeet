
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.MessageChannelDto;
import com.umeet.umeet.dtos.UserValidacionDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    //localhost:8090/msg/channel/1 obtiene todos los mensajes del canal indicado
    @ResponseBody
    @PostMapping("/channel/{id_channel}") //Devuelve un Json con todos los mensajes de un canal
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
    
    //localhost:8090/msg/private/5 (5 seria el usuario que esta obteniendo sus mensajes privados)
    @ResponseBody
    @PostMapping("/private/{id_user}") //Devuelve un Json con todos los mensajes privados
    public List<MessageChannelDto> privados(@PathVariable Long id_user){
        List<Message> aux = repoMsg.findAll();
        if (!aux.isEmpty()){
            List<Message> origen = aux.stream()
                      .filter(x->x.getUser().getId()==id_user && x.getUserDestiny()!=null) //Obtiene mensajes que el ha mandado
                      .collect(Collectors.toList());
            List<Message> destino = aux.stream()
                      .filter(x->x.getUserDestiny()!=null && x.getUserDestiny().getId()==id_user)                      //Obtiene mensajes que ha recibido
                      .collect(Collectors.toList());
            List<MessageChannelDto> res = origen.stream()
                                        .map(x->mapper.map(x, MessageChannelDto.class))
                                        .collect(Collectors.toList());
            res.addAll(destino.stream()
                            .map(x->mapper.map(x, MessageChannelDto.class))
                            .collect(Collectors.toList()));
           
            return res;
        }
        return null;
    }
    
    
    
    //De aqui para abajo no son json, se podria sacar a otro controller y dejar solo los Json en este
    @GetMapping
    public String inicio(Model m){
        //m.addAttribute("message",new Message());
        //return "/messages/vista";
        m.addAttribute("message",new Message());
        return "/messages/vista";
    }
    @ResponseBody
    @PostMapping("/channel/sendmsg") //Guarda mensajes en un canal por un usuario
    public void mensajeCanal(Message msg,Long idChannel){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msg.setChannel(repoChn.findById(idChannel).get());
        msg.setUser(repoUsr.findById(u.getId()).get());
        msg.setName(msg.getUser().getNickName());
        repoMsg.save(msg);
    }
    
    @ResponseBody
    @PostMapping("/private/sendmsg") //Guarda mensajes privados entre usuarios
    public void mensajePrivado(Message msg,Long idUserDestiny){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msg.setUser(repoUsr.findById(u.getId()).get());
        msg.setUserDestiny(repoUsr.findById(idUserDestiny).get());
        repoMsg.save(msg); 
    }
}
