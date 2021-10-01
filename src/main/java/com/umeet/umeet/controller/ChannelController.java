package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.ChannelDto;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.entities.Message;
import com.umeet.umeet.repositories.CategoryRepository;
import com.umeet.umeet.repositories.ChannelRepository;
import com.umeet.umeet.repositories.MessageFileRepository;
import com.umeet.umeet.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/b/channel")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFileRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{idChannel}")
    public ChannelDto getChannel(@PathVariable Long idChannel){
        Channel channel = channelRepository.findById(idChannel).get();
        ChannelDto channelDto = mapper.map(channel, ChannelDto.class);
        return channelDto;
    }

    @PostMapping("/addChannel")
    public ChannelDto addChannel(ChannelDto channelDto, Long idCategory){
        Channel channel = mapper.map(channelDto, Channel.class);
        channel.setCategory(categoryRepository.findById(idCategory).get());
        channel = channelRepository.save(channel);
        channelDto = mapper.map(channel, ChannelDto.class);
        return channelDto;
    }

    @GetMapping("/deleteChannel")
    public Long deleteChannel(Long idChannel){
        long idServer = channelRepository.findById(idChannel).get().getCategory().getServer().getId();
        channelRepository.deleteById(idChannel);
        return idServer;
    }
}
