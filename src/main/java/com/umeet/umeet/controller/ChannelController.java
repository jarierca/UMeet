package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.feign.ChannelFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelFeign chaFeign;

    @GetMapping("/form")
    public String viewChannelCreation(Model model, Long idChannel, Long idCategory) {

        Channel channel = chaFeign.viewChannelCreation(idChannel, idCategory);
        model.addAttribute("channel", channel);
        return "formChannel";
    }

    @PostMapping("/addChannel")
    public String addChannel(Channel channel, Long idCategory) {
        Channel cha = chaFeign.addChannel(channel.getId(), channel.getName(), idCategory);

        return "redirect:server/one?idServer=" + channel.getCategory().getServer().getId();
    }

    @GetMapping("/deleteChannel")
    public String deleteChannel(Long idChannel) {
       Long idServer = chaFeign.deleteChannel(idChannel);
        return "redirect:server/one?idServer=" + idServer;
    }
}
