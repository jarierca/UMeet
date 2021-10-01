
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.MessageChannelDto;
import com.umeet.umeet.dtos.MessageFileDto;
import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.entities.MessageFile;
import com.umeet.umeet.feign.MessagesFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/msg")
//@ResponseBody
public class MessagesController {
    
    @Autowired
    private MessagesFeign msgFeign;
    
    //localhost:8090/msg/channel/1 obtiene todos los mensajes del canal indicado
    @ResponseBody
    @PostMapping("/channel/{id_channel}") //Devuelve un Json con todos los mensajes de un canal
    public List<MessageChannelDto> canales(@PathVariable Long id_channel){
        List<MessageChannelDto> aux = msgFeign.canales(id_channel);
        if (!aux.isEmpty()){
            return aux;
        }
        return null;
    }
    
    //localhost:8090/msg/private/5 (5 seria el usuario que esta obteniendo sus mensajes privados)
    @ResponseBody
    @PostMapping("/private/{id_destino}") //Devuelve un Json con todos los mensajes privados entre el usuario logueado y el usuario destino
    public List<MessageChannelDto> privados(@PathVariable Long id_destino){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<MessageChannelDto> aux = msgFeign.privados(id_destino,u.getId());
        if (!aux.isEmpty()){
            return aux;          
        }
        return null;
    }
    
    
    @ResponseBody
    @PostMapping("/channel/sendmsg") //Guarda mensajes en un canal por un usuario
    public void mensajeCanal(Message msg,Long idChannel){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msgFeign.mensajeCanal(msg.getId(),msg.getName(),msg.getText(), idChannel, u.getId());
    }
    
    @GetMapping("/addFile")
    public String addFile(Model m, String type , Long id){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        m.addAttribute("idUser", u.getId());
        m.addAttribute("idChannel", id);
        if(type.equals("private")){
            type = "/msg/private/sendmsgfile";
        }else if(type.equals("channel")){
            type = "/msg/channel/sendmsgfile";
        }
        m.addAttribute("action", type);
        
        return "addFile";
    }
    
    @ResponseBody
    @PostMapping("/channel/sendmsgfile")
    public void mensajeFileCanal(String name, String text, MultipartFile archivo, Long id, Long idUser){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        msgFeign.mensajeFileCanal(u.getUsername(), "archivo", archivo, id, u.getId());
    }
    
    @ResponseBody
    @PostMapping("/private/sendmsg") //Guarda mensajes privados entre usuarios
    public void mensajePrivado(Message msg,Long idUserDestiny){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msgFeign.mensajePrivado(msg.getId(),msg.getName(),msg.getText(), idUserDestiny, u.getId());
    }
    
       
    @ResponseBody
    @PostMapping("/private/sendmsgfile")
    public void mensajeFilePrivado(MessageFile msgFile, MultipartFile archivo,Long id, Long idUser){
       UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        msgFeign.mensajeFilePrivado(u.getUsername(), "archivo", archivo, id, u.getId());
    }
}
