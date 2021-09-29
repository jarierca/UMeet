/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.feign;

import com.umeet.umeet.entities.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(/*name = "UMeetBack"*/name = "canales", url = "http://localhost:8082")
@RequestMapping("/b/category")
public interface ChannelFeign {

    @GetMapping("/form")
    public Channel viewChannelCreation(Long idChannel, Long idCategory);
   
    @PostMapping("/addChannel")
    public Channel addChannel(Channel channel, Long idCategory);
    
    @GetMapping("/deleteChannel")
    public Long deleteChannel(Long idChannel);

}
