package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.repositories.CategoryRepository;
import com.umeet.umeet.repositories.ChannelRepository;
import com.umeet.umeet.repositories.MessageFileRepository;
import com.umeet.umeet.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFileRepository;

    @GetMapping("/pruebaChannel")
    public String pruebaChannel(Model model, Long idCategory){
        model.addAttribute("channels", channelRepository.findByCategory(categoryRepository.findById(idCategory).get()));
        model.addAttribute("idCategory", idCategory);
        return "pruebaChannel";
    }

    @GetMapping("/form")
    public String viewChannelCreation(Model model, Long idChannel, Long idCategory){
        Channel channel = new Channel();
        if(idChannel==null){
            channel.setCategory(categoryRepository.findById(idCategory).get());
        } else {
            channel = channelRepository.findById(idChannel).get();
        }
        model.addAttribute("channel", channel);
        return "formChannel";
    }

    @PostMapping("/addChannel")
    public String addChannel(Channel channel, Long idCategory){
        channel.setCategory(categoryRepository.findById(idCategory).get());
        channelRepository.save(channel);
        return "redirect:/server/one?idServer="+channel.getCategory().getServer().getId();
    }

    @GetMapping("/deleteChannel")
    public String deleteChannel(Long idChannel){
        List<Message> messages = messageRepository.findByChannel(channelRepository.findById(idChannel).get());
        messages.stream().forEach(m->{
            messageFileRepository.deleteById(m.getMessageFile().getId());
            messageRepository.deleteById(m.getId());
        });
        long idServer = channelRepository.findById(idChannel).get().getCategory().getServer().getId();
        channelRepository.deleteById(idChannel);
        return "redirect:/server/one?idServer="+idServer;
    }
}
