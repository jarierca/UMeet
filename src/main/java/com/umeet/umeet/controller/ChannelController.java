package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.ChannelDto;
import com.umeet.umeet.dtos.ChannelParamDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.feign.CategoryFeign;
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

    @Autowired
    private CategoryFeign categoryFeign;

    @GetMapping("/form")
    public String viewChannelCreation(Model model, Long idChannel, Long idCategory) {
        ChannelDto channelDto = new ChannelDto();
        if(idChannel!=null){
            channelDto = chaFeign.getChannel(idChannel);
        }
        model.addAttribute("channel", channelDto);
        model.addAttribute("idCategory", idCategory);
        return "formChannel";
    }

    @PostMapping("/addChannel")
    public String addChannel(ChannelParamDto channel) {
        if(channel.getName()==null || ("").equals(channel.getName())){
            Long idServer = categoryFeign.getCategory(channel.getIdCategory()).getServer().getId();
            return "redirect:/server/one?idServer=" + idServer;
        }
        ChannelDto cha = chaFeign.addChannel(channel);
        return "redirect:/server/one?idServer=" + cha.getCategory().getServer().getId();
    }

    @GetMapping("/deleteChannel")
    public String deleteChannel(Long idChannel) {
        Long idServer = chaFeign.deleteChannel(idChannel);
        return "redirect:/server/one?idServer=" + idServer;
    }
}
