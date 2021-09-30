
package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.MessageChannelDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.entities.MessageFile;
import com.umeet.umeet.repositories.ChannelRepository;
import com.umeet.umeet.repositories.MessageFileRepository;
import com.umeet.umeet.repositories.MessageRepository;
import com.umeet.umeet.repositories.UserRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.umeet.umeet.feign.MessagesFeign;

@Controller
@RequestMapping("/msg")
//@ResponseBody
public class MessagesController {
    
    @Autowired
    private MessageRepository repoMsg;
   
    @Autowired
    private MessageFileRepository repoMsgFile;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChannelRepository repoChn;
    
    @Autowired
    private UserRepository repoUsr;
    
    @Value("${carpetas.recursos.umeet}")
    private String rutaRecursos; 
    
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
    
    /*@ResponseBody
    @PostMapping("/channel/sendFile")
    public void mensajeFileCanal(Message msg, MessageFile msgFile, MultipartFile archivo,Long id){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        msg.setChannel(repoChn.findById(id).get());
        msg.setUser(repoUsr.findById(u.getId()).get());
        msg.setName(msg.getUser().getNickName());
        msg.setText("Fichero Subido");
        
        repoMsg.save(msg);
        
        
        msgFile.setName(u.getUsername());
        
        String ruta = rutaRecursos + "/file/" + archivo.getOriginalFilename();
        File f = new File(ruta);
        f.getParentFile().mkdirs();
        try {
            Files.copy(archivo.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msgFile.setUrl(ruta);
        msgFile.setMessage(repoMsg.findById(msg.getId()).get());
        
        repoMsgFile.save(msgFile); 
    }*/
    
    @ResponseBody
    @PostMapping("/private/sendmsg") //Guarda mensajes privados entre usuarios
    public void mensajePrivado(Message msg,Long idUserDestiny){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msgFeign.mensajePrivado(msg.getId(),msg.getName(),msg.getText(), idUserDestiny, u.getId());
    }
    
       
    /*@ResponseBody
    @PostMapping("/private/sendFile")
    public void mensajeFilePrivado(MessageFile msgFile, MultipartFile archivo,Long id){
        UserValidacionDto u=(UserValidacionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        msgFile.setName(u.getUsername());
        
        String ruta = rutaRecursos + "/file/" + archivo.getOriginalFilename();
        File f = new File(ruta);
        f.getParentFile().mkdirs();
        try {
            Files.copy(archivo.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        msgFile.setUrl(ruta);
        
        repoMsgFile.save(msgFile); 
    }*/
}
