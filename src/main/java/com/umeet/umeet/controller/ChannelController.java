package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.feign.ChannelFeign;
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
    private ChannelFeign chaFeign;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFileRepository;

    @GetMapping("/form")
    public String viewChannelCreation(Model model, Long idChannel, Long idCategory) {

        Channel channel = chaFeign.viewChannelCreation(idChannel, idCategory);
        model.addAttribute("channel", channel);
        return "formChannel";
    }

    @PostMapping("/addChannel")
    public String addChannel(Channel channel, Long idCategory) {
        Channel cha = chaFeign.addChannel(channel, idCategory);

        return "redirect:server/one?idServer=" + channel.getCategory().getServer().getId();
    }

    @GetMapping("/deleteChannel")
    public String deleteChannel(Long idChannel) {
       Long idServer = chaFeign.deleteChannel(idChannel);
        return "redirect:server/one?idServer=" + idServer;
    }
}
