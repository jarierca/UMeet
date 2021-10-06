/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.ChannelDto;
import com.umeet.umeet.dtos.ChannelParamDto;
import com.umeet.umeet.entities.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "feignChannel", name = "UMeetBack")
@RequestMapping("/b/channel")
public interface ChannelFeign {

    @GetMapping("/form")
    public ChannelDto viewChannelCreation(@RequestParam Long idChannel, @RequestParam  Long idCategory);
   
    @PostMapping("/addChannel")
    public ChannelDto addChannel(@SpringQueryMap ChannelParamDto channelDto);
    
    @GetMapping("/deleteChannel")
    public Long deleteChannel(@RequestParam Long idChannel);

    @GetMapping("/{idChannel}")
    public ChannelDto getChannel(@PathVariable Long idChannel);

}
